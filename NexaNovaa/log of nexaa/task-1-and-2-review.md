# Review of Tasks 1 and 2

Assessment date: 7 October 2026. This is a code/document review, not an instructor grade. All PDF text was extracted; role-planning pages and selected flow/ER/class/use-case pages were rendered and visually inspected. The HTML review covered source structure and checks, not live browser rendering or mobile usability testing.

## Overall assessment

The first two tasks form a good starting point for a student project. The documentation explains the business process and the prototype covers most admin/counselor screens. Keep both stages as references. They need consistent requirements and a few interface fixes before serving as the specification for the functional build.

Prototype actions being simulated is appropriate for a frontend design stage. They must not be described as completed database or authentication features.

## Task 1: planning and system design

### What is done well

- The problem statement connects lost enquiries, missed follow-ups and counselor performance to the proposed CRM features.
- The enquiry -> assignment -> call -> follow-up -> admission workflow is documented at several levels.
- The 37-page document contains actual flow, activity, ER, class and use-case diagrams alongside explanations and tables.
- Users, courses, enquiries, call records, follow-ups and admissions are connected through clear primary/foreign-key relationships.
- The design considers validation, password hashes, active accounts, fees, reporting and unique admission conversion.
- Counselor restrictions and admin responsibilities are spelled out in the six-page role plan.

### What needs refinement

1. **Choose a consistent role scope.** The project document defines Admin, Counselor and Manager. The role document claims four roles but lists two, and mentions trainer/student data without defining those roles. Start with those three documented CRM roles; treat trainer/student portals as later extensions unless the assignment explicitly requires them.
2. **Unify status vocabulary.** The plan alternates between New/Called/Follow-up/Closed/Converted/CNR and Open/CNR/Call Back/Stage 2/Stage 2.5. Define what each stage means and permitted transitions. Keep call outcome distinct from overall lead stage.
3. **Resolve CNR handling.** The main flow diagram groups CNR with Not Interested and closes the enquiry, while the calling steps reschedule unreachable calls. Define CNR consistently so an unanswered call does not accidentally discard a viable lead.
4. **Align date/time fields.** Follow-up scheduling asks for a date and time, but the schema uses DATE. A due timestamp is needed if time-of-day reminders are required.
5. **Finish the lists/import model.** The role plan specifies lists, bulk assignment, CSV/Excel mapping and duplicates; the six-table design does not model lead lists or import batches. Define the relationship and duplicate rules before implementing bulk import.
6. **Make payment rules consistent.** The document mixes Installment/Pending Balance/payment status terms, and describes receipts and payment transactions more fully than the schema supports. Decide whether separate payment and installment records are needed.
7. **Add acceptance criteria.** Each feature needs a concrete pass condition, including invalid input, duplicates, unauthorized access and empty states. Avoid unnecessary duplicate user tables per role; the shared Users design is a sound starting point.

## Task 2: frontend prototype

### What is done well

- Seventeen pages cover nine admin functions, seven counselor functions and login.
- CSS variables, repeated sidebar navigation, cards, tables and status badges provide a consistent intended visual system.
- Lead capture includes the practical details in the role plan: contact information, qualification, institution, source, course and assignment.
- Bulk upload/mapping, calls, scheduling, fees and performance have visible prototype screens.
- Static local `.html` navigation targets checked in this audit all exist.

### What needs refinement

- **Manager isolation:** the login's Manager option opens the Admin dashboard with management navigation. Build a read-only Manager view and enforce permissions on the backend.
- **Real authentication:** login only checks that email/password are nonempty before redirecting. This is demo navigation, not a credential check.
- **Broken popup targets:** Add User calls `openModal('userModal')` on admin add-lead, assign-leads, courses, import, manage-leads, manage-list and reports pages that do not contain that modal. Remove the irrelevant shortcut or provide the target.
- **Persistence:** many save actions show success alerts; importing also simulates selecting a file. Replace each with a real request and display success only after saving.
- **Responsive behavior:** none of the 17 pages contains an `@media` rule; dashboards use a fixed sidebar and wide grids. Mobile/tablet behavior needs browser verification and responsive adjustments.
- **Dialer:** the source checks found no `tel:` links, although the role plan calls for clicking a number to open the dialer.
- **Maintainability:** CSS and modal helpers are duplicated across pages. Use one shared CSS file and small shared JavaScript helpers in the fresh build.
- **Form quality:** add actual form submission, field identifiers, associated labels, validation, pending/error feedback and keyboard-accessible dialogs.

## Scope recommendation

Reuse the documented business flow and the Task 2 visual direction. First prove one persistent workflow with real permissions. Add lists/import, automation, receipts and report exports after that core workflow works.
