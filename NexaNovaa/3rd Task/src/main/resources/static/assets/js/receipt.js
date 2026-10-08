import { request, session } from "./api.js";
import { escape as e, money, when, table, empty } from "./ui.js";
const container = document.querySelector("#receipt");
try {
  const current = await session();
  if (!current.user) { location.replace("/login.html"); }
  else {
    const id = new URLSearchParams(location.search).get("id");
    if (!/^\d+$/.test(id || "")) { throw new Error("Choose a receipt from the admission register."); }
    const data = await request("/leads/" + id);
    const admission = data.admission;
    if (!admission) { throw new Error("This enquiry has no confirmed admission."); }
    container.innerHTML = '<header><div class="brand"><span class="brand-mark">N</span><span>Nexaanova<small>ADMISSIONS CRM</small></span></div><div><h2>Admission receipt</h2><span class="muted small">Receipt NA-' +
      admission.id + '</span></div></header><p class="muted small">Issued ' + when(admission.createdAt) +
      ' (IST)</p><h2>' + e(data.lead.name) + '</h2><p class="muted">' + e(data.lead.phone) + " · " + e(data.lead.email || data.lead.city || "") +
      '</p><dl class="detail-grid"><div><dt>Course</dt><dd>' + e(admission.courseName) + "</dd></div><div><dt>Payment mode</dt><dd>" +
      e(admission.paymentMode) + "</dd></div></dl>" + table(["Description", "Amount"], "<tr><td>Agreed course fees</td><td>" + money(admission.totalFees) +
      '</td></tr><tr class="total-row"><td>Amount received</td><td>' + money(admission.feesPaid) + '</td></tr><tr class="total-row"><td>Balance due</td><td>' +
      money(admission.balance) + '</td></tr>') + '<p class="muted small">This receipt acknowledges the amount received at admission. Please retain it for your records.</p>' +
      (data.lead.remarks?.startsWith("Sample") ? '<p class="notice">Fictional practice record · Not a financial document.</p>' : "");
    document.querySelector("#print").classList.remove("hidden");
  }
} catch (problem) { container.innerHTML = empty("Receipt unavailable", problem.message); }
document.querySelector("#print").addEventListener("click", () => window.print());
