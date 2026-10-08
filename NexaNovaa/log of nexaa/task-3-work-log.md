# Task 3 implementation log

Date: **8 October 2026, IST (UTC+05:30)**. Recorded at the end of this implementation session, around 11:21 IST.

The user authorized starting fresh Task 3 with readable student-level Java, modest UI polish, and easy edits. The assistant implemented the changes during this chat. This log does not attribute the new code to independent student work or invent dates for Tasks 1/2.

## What was built

| Feature | Actual result | Main files / syllabus connection |
| --- | --- | --- |
| Clean Java application | 28 main Java source files; Spring Boot 3.5.16; Java release 17; builds with installed JDK 21 | pom.xml, CrmApplication; Java/Spring Boot/DI |
| Explicit layers and database | Controller → service → DAO; six MySQL tables; BIGINT IDs; decimal fees; explicit SQL | model/, service/, dao/, schema.sql; OOP/JDBC/DAO/SQL |
| Login/access | Servlet session, renewed session ID, inactive-account checks, form token, per-request role/ownership checks | AuthController, SessionFilter, Access, UserService; Servlets/layered apps |
| Password storage | Salted standard Java PBKDF2; no password hashes in returned account models | PasswordHelper; supporting detail, not a named syllabus topic |
| Enquiries and assignment | Required-field checks, normalized duplicate phones, search/stage filter, Admin assignment, Counselor ownership | LeadService, LeadDao, app.js |
| Calls | Stored notes/author/date; no-answer/interested/call-later/closed transitions | ActivityDao, LeadService |
| Follow-ups | Future IST dates, pending/completed/closed tasks, due dashboard, duplicate/concurrent completion checks | ActivityDao, LeadService |
| Admission | Single transactional conversion; exact BigDecimal balance; closes pending follow-ups; locks admitted enquiry | AdmissionDao, LeadService |
| Initial payment receipt | HTML receipt with browser print; recorded admission fees stay unchanged after catalogue edits | receipt.html, receipt.js; HTML/CSS/JS |
| Team and courses | Admin creation/activation/catalogue editing; Manager read-only team view | UserService, CourseService |
| Shared interface | One shell for all roles, reusable forms/tables/dialogs, CSS theme tokens, responsive layouts | index.html, assets/css/styles.css, assets/js/ui.js/app.js |
| Real summaries | Counts from saved permitted records; source/stage/counselor snapshots | app.js; JavaScript/collections |
| Local setup | Maven wrapper; separate localhost MySQL on 3307; generated ignored credentials; hidden Windows startup/shutdown helpers | scripts/, .mvn/; Maven and supporting local tooling |
| Edit guidance | Field/stage/theme/page recipes and requirements/access matrix | README, docs/architecture.md, requirements.md, manual-checks.md |

Maven is the syllabus-listed build tool. No Spring Security, JWT, Lombok, Spring Data JPA, Bean Validation, JUnit/Mockito, or optional frontend framework was added. Required Spring Boot HTTP/JSON/JDBC dependencies support the chosen framework. Browser verification tools ran externally from the ignored local tooling folder; they are not application dependencies.

## Observed milestones

| Time (IST) | Evidence |
| --- | --- |
| 10:39:13 | First new application configuration file's local creation timestamp |
| 10:55:29 | Separate MySQL 8.0.45 instance ready on localhost port 3307 |
| 11:00:54 | First new Spring Boot app reported startup on port 8080, with successful JDBC connection |
| 11:13:12 | 23 browser acceptance groups completed |
| 11:16:31 | Original 36 Task 1/2/Current Workspace files verified unchanged by SHA-256 |
| 11:16:53 | Final 29 API acceptance groups completed, including concurrent follow-up completion |
| 11:20:21 | Seven persistence checks passed after restarting the app and database |
| 11:20:40 | Final clean-workspace previews passed at 1440, 900, and 390 px; no console errors |

Evidence JSON timestamps are UTC ISO strings; the table converts them to IST. Filesystem creation time records this local implementation session, not historical task authorship.

## Verification actually performed

- Maven wrapper package build succeeded. The packaged application starts and connects without schema errors.
- **17 ordinary Java checks passed**: phone/email normalization, invalid fields/choices, exact money, password salts/verification, wrong/malformed credentials. Maven compiles these; the main method was run explicitly.
- **29 API acceptance groups passed**: role restrictions, CSRF/session renewal/logout, creation/assignment/reassignment, duplicate phones, call history/stages, follow-up dates/completion, monetary validation, account deactivation, converted-enquiry restrictions, historical fee snapshots, and concurrent requests.
- Simultaneous admission submissions returned one success and one conflict; exactly one admission exists. Simultaneous follow-up completion likewise succeeds exactly once.
- **23 browser acceptance groups passed**: real forms and view navigation, useful login errors, search/filter, escaped HTML-looking remarks, call/follow-up/admission forms, balance preview, receipt and print controls, Manager/Counselor controls, and absence of browser JavaScript/CSP errors.
- **Seven persistence checks passed** after stopping/restarting both local processes: counts, calls, admission stage/balance, and completed/closed follow-ups persisted. Initial seed records were not duplicated.
- Final desktop/tablet/mobile preview checks passed. Admissions currency cards fit mobile; large tables scroll within their cards. Print controls are hidden in print layout.
- Git correctly ignores .local configuration/database and target build output. The Task 3 repository was initialized on branch codex/task-3; no commit was created.
- All **36 earlier Task 1/2/Current Workspace files** still match the original SHA-256 inventory.

The original workspace retains complete evidence under verification/task-3/. This public copy includes selected results under verification/ and screenshots under ../3rd Task/docs/screenshots/. Final previews contain original sample data after verification cleanup. Private backups and local runtime files are excluded.

## Problems found and resolved

1. PowerShell split the original shorthand MySQL host argument; setup initially failed with unknown host '127'. Explicit quoted --host/--port arguments fixed it.
2. Windows MySQL process lifetime/file locking affected recovery of the first empty setup directory. The final helper uses standalone/console mode, a separate mysql-dev directory, process ownership checks, and graceful database shutdown. Temporary failed initialization folders remain ignored under .local; they are not reused.
3. Exact dropdown labeling failed a browser check. Shared select helpers now use explicit label/control associations.
4. Review of concurrent follow-up completion found that a snapshot read could become stale. Completion checks the conditional SQL update's affected-row count; the final concurrency check passed.

The existing MySQL service on 3306 and its data were not reset. Only exact IDs created by verification were cleaned from the new local database. Final practice counts: **8 enquiries, 4 accounts, 3 courses, 1 admission**.

## Remaining scope

Not implemented: CSV import/export, installments/payment ledger, date-range reports, password change/reset, Trainer/Student portals. Current reports are lifetime snapshots, and current list filtering loads permitted records into the browser. Schema initialization does not migrate changed existing columns.

This is a local student practice version, not a production deployment. Hosted operation needs its own HTTPS/cookie/configuration, backup/recovery, throttling, and operational checks.

## What the student should do next

1. Run the app and complete one enquiry journey using Admin and Counselor accounts.
2. Trace that request through controller, service, DAO, and database. Explain ownership, stages, and BigDecimal balance.
3. Read docs/architecture.md and try a small preferred-batch field using its edit recipe.
4. Then add CSV preview/import/export, followed by an installment ledger if required by the assignment.

Future sessions should add dated entries with what changed, what was checked, failures, and actual outcomes.
