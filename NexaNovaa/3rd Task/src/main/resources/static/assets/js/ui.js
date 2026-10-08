// Shared display helpers. Always escape data before putting it into HTML.
export function escape(value) {
  return String(value ?? "").replace(/[&<>"']/g, character => ({ "&": "&amp;", "<": "&lt;", ">": "&gt;", '"': "&quot;", "'": "&#39;" }[character]));
}
export function label(value) {
  const labels = { OPEN: "New enquiry", CNR: "No response", CALL_BACK: "Call back", INTERESTED: "Interested", FOLLOW_UP: "Follow-up", CONVERTED: "Admitted", CLOSED: "Closed", NO_ANSWER: "No answer", CALL_LATER: "Call later", NOT_INTERESTED: "Not interested", PENDING: "Pending", DONE: "Completed", ADMIN: "Administrator", COUNSELOR: "Counselor", MANAGER: "Manager" };
  return labels[value] || value || "—";
}
export function badge(value) { return '<span class="badge status-' + escape(value) + '">' + escape(label(value)) + "</span>"; }
export function money(value) { return new Intl.NumberFormat("en-IN", { style: "currency", currency: "INR", maximumFractionDigits: 2 }).format(Number(value || 0)); }
export function when(value) {
  if (!value) { return "—"; }
  // Database dates are local IST, so attach the explicit offset before formatting.
  return new Date(value + "+05:30").toLocaleString("en-IN", { day: "numeric", month: "short", hour: "numeric", minute: "2-digit", timeZone: "Asia/Kolkata" });
}
export function nowIST() {
  const parts = new Intl.DateTimeFormat("en-CA", { timeZone: "Asia/Kolkata", year: "numeric", month: "2-digit", day: "2-digit", hour: "2-digit", minute: "2-digit", second: "2-digit", hourCycle: "h23" }).formatToParts(new Date());
  const map = Object.fromEntries(parts.map(part => [part.type, part.value]));
  return map.year + "-" + map.month + "-" + map.day + "T" + map.hour + ":" + map.minute + ":" + map.second;
}
export function due(record) { return record.status === "PENDING" && record.dueAt <= nowIST(); }
export function options(values, selected, blank = "") {
  let html = blank ? '<option value="">' + escape(blank) + "</option>" : "";
  for (const item of values) {
    const value = typeof item === "object" ? item.id : item;
    const name = typeof item === "object" ? item.name : label(item);
    html += '<option value="' + escape(value) + '"' + (String(value) === String(selected) ? " selected" : "") + ">" + escape(name) + "</option>";
  }
  return html;
}
export function field(name, title, value = "", type = "text", extra = "") {
  return '<label>' + title + '<input name="' + name + '" type="' + type + '" value="' + escape(value) + '" ' + extra + "></label>";
}
export function select(name, title, values, selected, blank = "") {
  return '<div class="form-field"><label for="field-' + name + '">' + title + '</label><select id="field-' + name + '" name="' + name + '">' + options(values, selected, blank) + "</select></div>";
}
export function textarea(name, title, value = "", required = false) {
  return '<label class="span-two">' + title + '<textarea name="' + name + '" rows="3" maxlength="2000"' + (required ? " required" : "") + ">" + escape(value) + "</textarea></label>";
}
export function action(name, text, id = "", classes = "btn secondary") {
  return '<button type="button" class="' + classes + '" data-action="' + name + '" data-id="' + escape(id) + '">' + text + "</button>";
}
export function empty(title, description) { return '<div class="empty-state"><span aria-hidden="true">◌</span><h3>' + escape(title) + '</h3><p class="muted">' + escape(description) + "</p></div>"; }
export function table(headings, rows) {
  return '<div class="table-scroll"><table><thead><tr>' + headings.map(heading => "<th>" + heading + "</th>").join("") + "</tr></thead><tbody>" + rows + "</tbody></table></div>";
}
export function openModal(title, html) {
  document.querySelector("#modal-title").textContent = title;
  document.querySelector("#modal-body").innerHTML = html;
  const modal = document.querySelector("#modal");
  if (!modal.open) { modal.showModal(); }
  document.querySelector("#modal-body input, #modal-body select, #modal-body button")?.focus();
}
export function closeModal() { document.querySelector("#modal").close(); }
let toastTimer;
export function toast(message) {
  const element = document.querySelector("#toast");
  element.textContent = message; element.classList.remove("hidden");
  clearTimeout(toastTimer);
  toastTimer = setTimeout(() => element.classList.add("hidden"), 4500);
}
