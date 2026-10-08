# Improvements from the original work

Updated 8 October 2026 (IST).

Task 1 established the requirements, roles, diagrams, and admission journey. Task 2 supplied 17 HTML/CSS/JavaScript prototype screens. Those remain useful design references. The fresh Task 3 implements the first complete journey in Java and MySQL.

| Area | Original prototype / unfinished Task 3 | Fresh Task 3 |
| --- | --- | --- |
| Login | Nonempty credentials redirected; earlier backend authentication was incomplete | Real account lookup, salted password hashing, session renewal, logout, inactive-account checks |
| Access | Manager opened the Admin dashboard; counselor isolation was not demonstrated | Read-only Manager; server-enforced counselor ownership and Admin-only management |
| Saving | Many save/import actions showed simulated alerts | Java API requests save to MySQL; records survive restarts |
| Enquiry stages | Open/CNR/Stage 2/Stage 2.5 vocabulary and CNR closure rules differed | One stored dictionary with clear display labels and transitions; unanswered calls remain retryable |
| Validation | Planned rules and prototype field checks | Java checks required fields, normalized duplicate phones, listed values, active assignments/courses, dates, and fees |
| Calls/follow-ups | Screens and planned operations | Saved call history/author/date, future IST follow-ups, due lists, completion/closure |
| Admission | Screens and planned conversion/payment behavior | Transactional single conversion, exact BigDecimal balance, pending follow-up closure, HTML print receipt |
| Reports | Sample counts | Counts and source/stage/counselor summaries from permitted saved records |
| Maintainability | Repeated full-page CSS/modal helpers | Shared CSS, one page shell, reusable forms/tables/dialogs, explicit Java controller/service/DAO layers |
| Mobile | Fixed sidebars/grids without responsive media queries | Checked desktop/tablet/mobile layouts and contained table scrolling |
| Database | Earlier run had conflicting SQL/Java ID types and schema warnings | Consistent BIGINT/Long IDs and explicit schema/DAO SQL |
| Verification | Recorded startup did not establish a complete working journey | Java, API, browser, concurrency, and restart/persistence checks |

## Verified on 8 October 2026

- 17 ordinary Java checks.
- 29 API acceptance groups, including simultaneous admission and follow-up submissions.
- 23 browser acceptance groups, including saved forms, receipts, escaped text, and role-specific views.
- Seven persistence checks after restarting the app and database.
- Final preview at 1440, 900, and 390 px, with no page overflow or console errors.
- All 36 Task 1/2/Current Workspace files preserved.

The API/browser checks were performed with local verification tooling, not a new application/test framework. Repeatable acceptance cases are in [manual-checks.md](manual-checks.md); selected results are in [the project log](../../log%20of%20nexaa/README.md).

## Still pending

CSV import/export, installment/payment ledger, date-range reports, password change/reset, and Trainer/Student portals. Original screens for some of these remain prototypes; their presence does not mean the fresh backend implements them.

## Easy next edits

Change theme tokens in styles.css, modify shared forms in ui.js/app.js, or add a preferred-batch field using [the architecture/edit guide](architecture.md). Extend one working feature at a time.
