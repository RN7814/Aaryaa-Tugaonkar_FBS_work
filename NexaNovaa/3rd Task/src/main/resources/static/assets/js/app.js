import { request, session } from "./api.js";
import { escape as e, label, badge, money, when, due, nowIST, options, field, select, textarea, action, empty, table, openModal, closeModal, toast } from "./ui.js";

// Each view below uses the same state, CSS, table helper, and dialog.
const state = { user: null, options: {}, leads: [], followups: [], admissions: [], users: [] };
const content = document.querySelector("#content");
const pageAction = document.querySelector("#page-action");
const views = {
  dashboard: ["Overview", "A clear picture of your enquiries and the next conversations."],
  leads: ["Enquiries", "Keep student details, conversations, and next steps together."],
  followups: ["Follow-ups", "Make time for the conversations that move things forward."],
  admissions: ["Admissions", "Students who have taken their next step with Nexaanova."],
  reports: ["Reports", "A simple picture of the enquiries in your workspace."],
  users: ["Team", "Create accounts and manage access to the workspace."],
  courses: ["Courses", "Update your course catalogue in one place."]
};
const canWrite = () => state.user.role !== "MANAGER";
const isAdmin = () => state.user.role === "ADMIN";
const activeCourses = () => state.options.courses.filter(course => course.active);
const leadById = id => state.leads.find(lead => lead.id === Number(id));

async function loadData() {
  const [settings, leads, followups, admissions] = await Promise.all([
    request("/options"), request("/leads"), request("/followups"), request("/admissions")
  ]);
  state.options = settings; state.leads = leads; state.followups = followups; state.admissions = admissions;
  if (isAdmin()) { state.users = await request("/users"); }
}

function card(title, body, subtitle = "", link = "") {
  return '<section class="card"><header class="card-header"><div><h2>' + title + "</h2>" +
    (subtitle ? "<p>" + subtitle + "</p>" : "") + "</div>" + link + "</header>" + body + "</section>";
}

function stat(title, value, hint, symbol) {
  return '<div class="stat' + (String(value).includes("₹") ? " money-stat" : "") + '"><div class="stat-label">' + title + '<span class="stat-symbol" aria-hidden="true">' + symbol +
    "</span></div><strong>" + value + "</strong><small>" + hint + "</small></div>";
}

function leadRows(records, compact = false) {
  return records.map(lead => "<tr><td>" + action("detail", e(lead.name), lead.id, "link-button") + '<span class="muted">' +
    e(lead.phone) + "</span></td><td>" + e(lead.courseName || "Undecided") + "</td>" +
    (compact ? "" : "<td>" + e(lead.counselorName || "Unassigned") + "</td><td>" + e(lead.source) + "</td>") +
    "<td>" + badge(lead.stage) + "</td>" + (compact ? "" : "<td>" + when(lead.createdAt) + "</td>") + "</tr>").join("");
}

function followupCard(record) {
  return '<div class="followup-item"><div class="row-between">' + action("detail", e(record.leadName), record.leadId, "link-button") +
    (due(record) ? '<span class="overdue">Due now</span>' : '<span class="muted small">Upcoming</span>') +
    "</div><p>" + e(record.notes) + '</p><span class="muted small">' + when(record.dueAt) + " · " + e(record.userName) + "</span></div>";
}

function pipeline() {
  return '<div class="card-body pipeline">' + state.options.stages.map(stage => {
    const count = state.leads.filter(lead => lead.stage === stage).length;
    return '<div class="pipeline-row"><span>' + e(label(stage)) + '</span><progress max="' + Math.max(state.leads.length, 1) +
      '" value="' + count + '" aria-label="' + e(label(stage)) + '">' + count + "</progress><strong>" + count + "</strong></div>";
  }).join("") + "</div>";
}

function dashboard() {
  const open = state.leads.filter(lead => !["CONVERTED", "CLOSED"].includes(lead.stage)).length;
  const pending = state.followups.filter(record => record.status === "PENDING");
  const dueCount = pending.filter(due).length;
  const conversion = state.leads.length ? Math.round(state.admissions.length * 100 / state.leads.length) : 0;
  content.innerHTML = '<div class="stats-grid">' +
    stat("Total enquiries", state.leads.length, isAdmin() || state.user.role === "MANAGER" ? "Across the team" : "Assigned to you", "◉") +
    stat("Active conversations", open, "New, interested & follow-up", "↗") +
    stat("Follow-ups due", dueCount, pending.length + " pending in total", "◷") +
    stat("Admissions", state.admissions.length, conversion + "% of enquiries converted", "✓") + "</div>" +
    '<div class="dashboard-grid">' +
    card("Recent enquiries", state.leads.length ? table(["Student", "Course", "Stage"], leadRows(state.leads.slice(0, 5), true)) :
      empty("Your first enquiry starts here", "Add a student to begin the admission journey."), "Your five latest student enquiries", '<a class="small" href="#leads">View all →</a>') +
    card("Next conversations", pending.length ? pending.slice(0, 4).map(followupCard).join("") : empty("All caught up", "Scheduled follow-ups will appear here."),
      "Pending follow-ups, earliest first", '<a class="small" href="#followups">View all →</a>') + "</div>" +
    card("Enquiry pipeline", pipeline(), "Current stage of every enquiry in your view") +
    '<div class="summary-strip"><div><h3>Every conversation is a chance to help.</h3><p>Record a call, set a next step, and keep your student’s journey moving.</p></div><a class="btn secondary" href="#leads">Open enquiries →</a></div>';
}

function leadsView() {
  content.innerHTML = card("All enquiries", '<div class="toolbar"><input id="lead-search" type="search" aria-label="Search enquiries" placeholder="Search name, phone, or college"><select id="stage-filter" aria-label="Filter by stage">' +
    options(state.options.stages, "", "All stages") + '</select><span id="lead-count" class="muted"></span></div><div id="lead-table"></div>');
  renderLeadTable();
}

function renderLeadTable() {
  const query = document.querySelector("#lead-search").value.trim().toLowerCase();
  const stage = document.querySelector("#stage-filter").value;
  const records = state.leads.filter(lead => (!stage || lead.stage === stage) &&
    [lead.name, lead.phone, lead.college, lead.email].some(value => String(value || "").toLowerCase().includes(query)));
  document.querySelector("#lead-count").textContent = records.length + " of " + state.leads.length + " enquiries";
  document.querySelector("#lead-table").innerHTML = records.length ? table(["Student", "Course", "Counselor", "Source", "Stage", "Created"], leadRows(records)) :
    empty("No enquiries found", state.leads.length ? "Try another search or stage." : "Add your first student enquiry.");
}

function followupsView() {
  const pending = state.followups.filter(record => record.status === "PENDING");
  const other = state.followups.filter(record => record.status !== "PENDING");
  const rows = records => records.map(record => "<tr><td>" + action("detail", e(record.leadName), record.leadId, "link-button") +
    '<span class="muted">' + e(record.phone) + "</span></td><td>" + when(record.dueAt) + (due(record) ? '<span class="overdue">Due now</span>' : "") +
    '</td><td class="note-text">' + e(record.notes) + "</td><td>" + badge(record.status) + "</td><td>" +
    (canWrite() && record.status === "PENDING" ? action("complete", "Mark done", record.id, "link-button") : e(record.userName)) + "</td></tr>").join("");
  content.innerHTML = card("Pending follow-ups · " + pending.length, pending.length ? table(["Student", "Due (IST)", "Next conversation", "Status", canWrite() ? "Action" : "Recorded by"], rows(pending)) :
    empty("No pending follow-ups", "Open an enquiry to schedule the next conversation.")) +
    (other.length ? card("Completed & closed", table(["Student", "Due (IST)", "Notes", "Status", "Recorded by"], rows(other))) : "");
}

function admissionsView() {
  const total = state.admissions.reduce((sum, admission) => sum + Number(admission.totalFees), 0);
  const paid = state.admissions.reduce((sum, admission) => sum + Number(admission.feesPaid), 0);
  const rows = state.admissions.map(admission => "<tr><td>" + action("detail", e(admission.leadName), admission.leadId, "link-button") +
    '<span class="muted">Admission #' + admission.id + "</span></td><td>" + e(admission.courseName) + "</td><td>" +
    money(admission.totalFees) + "</td><td>" + money(admission.feesPaid) + "</td><td>" + money(admission.balance) +
    '</td><td><a href="/receipt.html?id=' + admission.leadId + '" target="_blank" rel="noopener">Receipt ↗</a></td></tr>').join("");
  content.innerHTML = '<div class="stats-grid">' + stat("Admissions", state.admissions.length, "Confirmed students", "✓") +
    stat("Total fees", money(total), "Agreed course fees", "₹") + stat("Amount received", money(paid), "Recorded at admission", "₹") +
    stat("Balance due", money(total - paid), "Outstanding course fees", "₹") + "</div>" +
    card("Admission register", rows ? table(["Student", "Course", "Total fees", "Paid", "Balance", "Receipt"], rows) :
      empty("No admissions yet", "Open an active enquiry and choose Admit student."), "Fees shown here are recorded at the time of admission.");
}

function reportsView() {
  const counselors = state.user.role === "COUNSELOR" ? state.options.counselors.filter(user => user.id === state.user.id) : state.options.counselors;
  const rows = counselors.map(user => {
    const assigned = state.leads.filter(lead => lead.counselorId === user.id);
    const converted = assigned.filter(lead => lead.stage === "CONVERTED").length;
    const pending = state.followups.filter(record => record.status === "PENDING" && assigned.some(lead => lead.id === record.leadId));
    return "<tr><td>" + e(user.name) + "</td><td>" + assigned.length + "</td><td>" + converted + "</td><td>" + pending.length +
      "</td><td>" + (assigned.length ? Math.round(converted * 100 / assigned.length) : 0) + "%</td></tr>";
  }).join("");
  content.innerHTML = '<div class="dashboard-grid">' + card("Enquiry stages", pipeline(), "Counts reflect your permitted enquiries.") +
    card("Source summary", table(["Source", "Enquiries"], state.options.sources.map(source => "<tr><td>" + e(source) + "</td><td>" +
      state.leads.filter(lead => lead.source === source).length + "</td></tr>").join(""))) + "</div>" +
    card("Counselor summary", table(["Counselor", "Assigned", "Admissions", "Pending follow-ups", "Conversion"], rows), state.user.role === "COUNSELOR" ?
      "Your own records only; other counselors’ counts are outside your view." : "A current snapshot; counts follow the present assignment.") +
    '<p class="muted small">These are lifetime counts for the records currently in this workspace. Date-range reports and payment installments can be added later.</p>';
}

function usersView() {
  content.innerHTML = card("Team members", table(["Name", "Email", "Role", "Status", "Access"], state.users.map(user => "<tr><td>" +
    e(user.name) + "</td><td>" + e(user.email) + "</td><td>" + e(label(user.role)) + "</td><td>" + (user.active ? "Active" : "Inactive") +
    "</td><td>" + (user.id === state.user.id ? '<span class="muted">Your account</span>' :
      action("toggle-user", user.active ? "Deactivate" : "Activate", user.id, "link-button")) + "</td></tr>").join("")),
    "Deactivating an account blocks its current session too.");
}

function coursesView() {
  content.innerHTML = card("Course catalogue", state.options.courses.length ? table(["Course", "Duration", "Default fees", "Availability", "Action"],
    state.options.courses.map(course => "<tr><td><strong>" + e(course.name) + "</strong></td><td>" + e(course.duration) + "</td><td>" +
    money(course.fees) + "</td><td>" + (course.active ? "Available" : "Inactive") + "</td><td>" + action("course", "Edit course", course.id, "link-button") + "</td></tr>").join("")) :
    empty("Build your catalogue", "Add a course before recording an admission."), "Default fees prefill the admission form; historical admission fees stay as recorded.");
}

function render() {
  let view = location.hash.slice(1) || "dashboard";
  if (!views[view] || (["users", "courses"].includes(view) && !isAdmin())) { view = "dashboard"; }
  document.querySelector("#page-title").textContent = views[view][0];
  document.querySelector("#page-description").textContent = views[view][1];
  document.title = views[view][0] + " · Nexaanova";
  document.querySelectorAll("[data-view]").forEach(link => {
    const active = link.dataset.view === view;
    link.classList.toggle("active", active);
    if (active) { link.setAttribute("aria-current", "page"); } else { link.removeAttribute("aria-current"); }
  });
  const add = canWrite() && ["dashboard", "leads"].includes(view) ? ["lead", "+ Add enquiry"] :
    isAdmin() && view === "users" ? ["user", "+ Add member"] : isAdmin() && view === "courses" ? ["course", "+ Add course"] : null;
  pageAction.classList.toggle("hidden", !add);
  if (add) { pageAction.dataset.action = add[0]; pageAction.dataset.id = ""; pageAction.textContent = add[1]; }
  ({ dashboard, leads: leadsView, followups: followupsView, admissions: admissionsView, reports: reportsView, users: usersView, courses: coursesView })[view]();
}

function form(actionName, fields, id = "", hint = "") {
  return '<form data-form="' + actionName + '" data-id="' + e(id) + '">' + (hint ? '<p class="form-hint">' + hint + "</p>" : "") +
    '<div class="form-grid">' + fields + '</div><p class="form-error" role="alert"></p><div class="form-footer">' +
    action("cancel", "Cancel") + '<button class="btn primary" type="submit">Save ' + (actionName === "admit" ? "admission" : "changes") + "</button></div></form>";
}

function leadForm(id) {
  const lead = leadById(id) || {};
  const fields = field("name", "Student name *", lead.name, "text", 'maxlength="100" required autocomplete="name"') +
    field("phone", "Phone number *", lead.phone, "tel", 'maxlength="22" required autocomplete="tel" placeholder="10-digit Indian number"') +
    field("email", "Email", lead.email, "email", 'maxlength="150" autocomplete="email"') +
    field("city", "City", lead.city, "text", 'maxlength="100"') +
    field("college", "College", lead.college, "text", 'maxlength="150"') +
    field("qualification", "Qualification", lead.qualification, "text", 'maxlength="80"') +
    select("courseId", "Interested course", activeCourses(), lead.courseId, "Undecided") +
    select("source", "Enquiry source *", state.options.sources, lead.source || "Walk-in") +
    (isAdmin() && !id ? select("counselorId", "Assign to", state.options.counselors, "", "Assign later") : "") +
    textarea("remarks", "Remarks", lead.remarks);
  openModal(id ? "Edit enquiry" : "Add a student enquiry", form("lead", fields, id, "Required fields are marked *. Phone numbers are checked for duplicates."));
}

async function detail(id) {
  const data = await request("/leads/" + id);
  const lead = data.lead;
  const editable = canWrite() && lead.stage !== "CONVERTED";
  const active = editable && lead.stage !== "CLOSED";
  const information = [["Course", lead.courseName || "Undecided"], ["Counselor", lead.counselorName || "Unassigned"], ["Email", lead.email || "—"], ["Source", lead.source],
    ["City", lead.city || "—"], ["College", lead.college || "—"], ["Qualification", lead.qualification || "—"], ["Created", when(lead.createdAt)]];
  let html = '<div class="detail-heading">' + badge(lead.stage) + '<p class="muted"><a href="tel:' + e(lead.phone) + '">' + e(lead.phone) + "</a></p></div>" +
    '<dl class="detail-grid">' + information.map(([name, value]) => "<div><dt>" + name + "</dt><dd>" + e(value) + "</dd></div>").join("") + "</dl>" +
    (lead.remarks ? '<p class="note-text muted small">' + e(lead.remarks) + "</p>" : "") +
    '<div class="action-row">' + (editable ? action("lead", "Edit details", id) : "") +
    (isAdmin() && editable ? action("assign", "Assign counselor", id) : "") +
    (active ? action("call", "Log a call", id) + action("followup", "Schedule follow-up", id) + action("admit", "Admit student", id, "btn primary") : "") +
    (editable ? action("stage", "Change stage", id) : "") + "</div>";
  if (data.admission) {
    const admission = data.admission;
    html += '<h3 class="history-heading">Admission confirmed</h3><div class="balance-preview"><span>Paid ' + money(admission.feesPaid) +
      "</span><strong>Balance " + money(admission.balance) + '</strong></div><p><a href="/receipt.html?id=' + id + '" target="_blank" rel="noopener">Open printable receipt →</a></p>';
  }
  html += '<h3 class="history-heading">Conversations · ' + data.calls.length + "</h3>" +
    (data.calls.length ? data.calls.map(call => '<div class="history-item"><strong class="small">' + e(label(call.outcome)) + '</strong><p>' +
      e(call.notes) + "</p><small>" + e(call.userName) + " · " + when(call.createdAt) + "</small></div>").join("") : '<p class="muted small">No calls recorded yet.</p>') +
    '<h3 class="history-heading">Follow-ups · ' + data.followups.length + "</h3>" +
    (data.followups.length ? data.followups.map(record => '<div class="history-item">' + badge(record.status) + "<p>" + e(record.notes) +
      "</p><small>" + when(record.dueAt) + "</small>" + (canWrite() && record.status === "PENDING" ? " · " + action("complete", "Mark done", record.id, "link-button") : "") +
      "</div>").join("") : '<p class="muted small">No follow-ups scheduled yet.</p>');
  openModal(lead.name, html);
}

function activityForm(name, id) {
  const lead = leadById(id);
  if (!lead) { throw new Error("Refresh the workspace and try again."); }
  let fields;
  let title;
  if (name === "assign") { title = "Assign counselor"; fields = select("counselorId", "Counselor", state.options.counselors, lead.counselorId, "Unassigned"); }
  if (name === "stage") { title = "Change enquiry stage"; fields = select("stage", "Stage", state.options.stages.filter(stage => stage !== "CONVERTED"), lead.stage); }
  if (name === "call") {
    title = "Record a conversation";
    fields = select("outcome", "Call outcome *", state.options.outcomes, "INTERESTED") + textarea("notes", "Conversation notes *", "", true);
  }
  if (name === "followup") {
    title = "Schedule a follow-up";
    fields = field("dueAt", "Due date & time (IST) *", "", "datetime-local", 'required min="' + nowIST().slice(0, 16) + '"') +
      textarea("notes", "Next conversation *", "", true);
  }
  if (name === "admit") {
    title = "Admit student";
    const selected = activeCourses().find(course => course.id === lead.courseId) || activeCourses()[0];
    if (!selected) { throw new Error("Ask the administrator to add an active course first."); }
    fields = select("courseId", "Course *", activeCourses(), selected.id) +
      select("paymentMode", "Payment mode *", state.options.payments, "Online") +
      field("totalFees", "Agreed total fees (₹) *", selected.fees, "number", 'min="0.01" max="99999999.99" step="0.01" required') +
      field("feesPaid", "Amount received (₹) *", "0", "number", 'min="0" max="99999999.99" step="0.01" required') +
      '<div class="balance-preview span-two"><span>Balance due</span><strong id="balance-value">' + money(selected.fees) + "</strong></div>";
  }
  openModal(title, form(name, fields, id, '<strong>' + e(lead.name) + "</strong> · " + e(lead.phone) +
    (name === "admit" ? "<br>Confirm the details carefully. Admission locks this enquiry and closes pending follow-ups." : "")));
}

function userForm() {
  openModal("Add a team member", form("user", field("name", "Name *", "", "text", 'required maxlength="100"') +
    field("email", "Email *", "", "email", 'required maxlength="150"') +
    select("role", "Role *", ["COUNSELOR", "MANAGER", "ADMIN"], "COUNSELOR") +
    field("password", "Initial password *", "", "password", 'required minlength="12" maxlength="128" autocomplete="new-password"'),
    "", "Use at least 12 characters for the initial password. Share account details with the person privately."));
}

function courseForm(id) {
  const course = state.options.courses.find(record => record.id === Number(id)) || {};
  openModal(id ? "Edit course" : "Add a course", form("course", field("name", "Course name *", course.name, "text", 'required maxlength="100"') +
    field("duration", "Duration *", course.duration, "text", 'required maxlength="60" placeholder="e.g. 6 months"') +
    field("fees", "Default fees (₹) *", course.fees || "", "number", 'required min="0.01" max="99999999.99" step="0.01"') +
    (id ? select("active", "Available for new admissions", [{ id: "true", name: "Available" }, { id: "false", name: "Inactive" }], String(course.active)) : ""), id));
}

async function refresh(message) {
  await loadData();
  render();
  if (message) { toast(message); }
}

document.addEventListener("click", async event => {
  const button = event.target.closest("[data-action]");
  if (!button || button.disabled) { return; }
  const name = button.dataset.action;
  const id = button.dataset.id;
  try {
    if (name === "cancel") { closeModal(); }
    if (name === "detail") { await detail(id); }
    if (name === "lead") { leadForm(id); }
    if (["assign", "stage", "call", "followup", "admit"].includes(name)) { activityForm(name, id); }
    if (name === "user") { userForm(); }
    if (name === "course") { courseForm(id); }
    if (name === "complete") {
      button.disabled = true;
      await request("/followups/" + id + "/complete", "PUT");
      closeModal(); await refresh("Follow-up marked as completed.");
    }
    if (name === "toggle-user") {
      const user = state.users.find(record => record.id === Number(id));
      openModal(user.active ? "Deactivate account?" : "Activate account?", form("toggle-user",
        '<p class="span-two form-hint">' + e(user.name) + (user.active ? " will lose access. Their assigned enquiries will stay saved; reassign them separately if needed." : " will be able to sign in again.") + "</p>", id));
    }
  } catch (problem) { toast(problem.message); }
  finally { button.disabled = false; }
});

document.addEventListener("submit", async event => {
  const element = event.target.closest("[data-form]");
  if (!element) { return; }
  event.preventDefault();
  const name = element.dataset.form;
  const id = element.dataset.id;
  const data = Object.fromEntries(new FormData(element));
  const submit = element.querySelector('[type="submit"]');
  const error = element.querySelector(".form-error");
  submit.disabled = true; error.textContent = "";
  try {
    for (const key of ["courseId", "counselorId"]) {
      if (key in data) { data[key] = data[key] ? Number(data[key]) : null; }
    }
    if (name === "lead") { await request("/leads" + (id ? "/" + id : ""), id ? "PUT" : "POST", data); }
    if (name === "assign") { await request("/leads/" + id + "/assignment", "PUT", data); }
    if (name === "stage") { await request("/leads/" + id + "/stage", "PUT", data); }
    if (name === "call") { await request("/leads/" + id + "/calls", "POST", data); }
    if (name === "followup") { await request("/leads/" + id + "/followups", "POST", data); }
    if (name === "admit") { await request("/leads/" + id + "/admission", "POST", data); }
    if (name === "user") { await request("/users", "POST", data); }
    if (name === "toggle-user") {
      const user = state.users.find(record => record.id === Number(id));
      await request("/users/" + id + "/active", "PUT", { active: !user.active });
    }
    if (name === "course") { data.active = id ? data.active === "true" : true; await request("/courses" + (id ? "/" + id : ""), id ? "PUT" : "POST", data); }
    closeModal();
    await refresh(name === "admit" ? "Admission confirmed. The receipt is ready." : "Saved to your workspace.");
    if (id && ["lead", "assign", "stage", "call", "followup", "admit"].includes(name)) { await detail(id); }
  } catch (problem) { error.textContent = problem.message; }
  finally { submit.disabled = false; }
});

document.addEventListener("input", event => {
  if (event.target.id === "lead-search") { renderLeadTable(); }
  if (["totalFees", "feesPaid"].includes(event.target.name)) { updateBalance(); }
});
document.addEventListener("change", event => {
  if (event.target.id === "stage-filter") { renderLeadTable(); }
  if (event.target.name === "courseId" && event.target.closest('[data-form="admit"]')) {
    const course = activeCourses().find(record => record.id === Number(event.target.value));
    event.target.form.elements.totalFees.value = course.fees;
    updateBalance();
  }
});
function updateBalance() {
  const form = document.querySelector('[data-form="admit"]');
  if (form) { document.querySelector("#balance-value").textContent = money(Number(form.elements.totalFees.value) - Number(form.elements.feesPaid.value)); }
}
document.querySelector("#close-modal").addEventListener("click", closeModal);
document.querySelector("#logout").addEventListener("click", async () => {
  try { await request("/auth/logout", "POST"); location.replace("/login.html"); } catch (problem) { toast(problem.message); }
});
window.addEventListener("hashchange", () => { if (state.user) { closeModal(); render(); } });
document.querySelector("#today").textContent = new Date().toLocaleDateString("en-IN", { day: "numeric", month: "short", year: "numeric", timeZone: "Asia/Kolkata" });

try {
  const current = await session();
  if (!current.user) { location.replace("/login.html"); }
  else {
    state.user = current.user;
    document.querySelector("#user-name").textContent = current.user.name;
    document.querySelector("#user-role").textContent = label(current.user.role);
    document.querySelector("#avatar").textContent = current.user.name.split(" ").slice(0, 2).map(word => word[0]).join("");
    document.querySelectorAll(".admin-only").forEach(link => link.classList.toggle("hidden", !isAdmin()));
    await loadData();
    document.querySelector("#sample-notice").classList.toggle("hidden", !state.options.sampleData);
    document.querySelector("#read-only-notice").classList.toggle("hidden", canWrite());
    render();
  }
} catch (problem) { content.innerHTML = empty("Could not load the workspace", problem.message) + action("reload", "Try again"); }
document.addEventListener("click", event => {
  if (event.target.closest('[data-action="reload"]')) { location.reload(); }
});
