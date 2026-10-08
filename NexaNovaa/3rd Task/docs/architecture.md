# Structure and easy edits

## Follow a request

Browser form → controller → service → DAO → MySQL.

Controllers read JSON and pass the signed-in user to a service. Services check permissions/business rules. DAOs hold parameterized SQL and map rows to plain Java models. The browser reloads saved data from the server.

- `model/`: ordinary fields with explicit getters/setters.
- `controller/CrmController.java`: CRM requests; `AuthController.java`: session login/logout.
- `service/`: validation, ownership, assignment, calls, follow-ups, conversion.
- `dao/`: SQL/JDBC row mapping; no generated repositories.
- `util/CrmOptions.java`: choices shared by the Java API and browser.
- `config/SessionFilter.java`: identity/active account/form token/Manager write checks.
- `config/InitialData.java`: optional fictional samples on the first empty-database start.
- `static/assets/js/app.js`: view functions and action forms.
- `static/assets/js/ui.js`: escaped text, tables/forms/dialogs.
- `static/assets/css/styles.css`: shared responsive styles and theme tokens.

Java paths above are under `src/main/java/com/nexaanova/crm`; static files are under `src/main/resources`. One shell with hash navigation serves all roles. Java enforces access even if someone bypasses hidden UI buttons.

## Add a lead field

For a preferred batch field:

1. Add a field/getter/setter to `model/Lead.java`.
2. Add its column to `schema.sql` for new databases.
3. For an existing database, prepare a reviewed `ALTER TABLE` against the practice database. `CREATE TABLE IF NOT EXISTS` does not add columns.
4. Update Lead DAO's row mapper and insert/update SQL.
5. Validate it in `LeadService.validate`.
6. Add its input in `leadForm` and display in `detail`, in `app.js`.
7. Check create/edit/reload and permitted/forbidden roles.

The existing shared grid/input styles handle the field without changing the layout.

## Other edits

Change the CSS variables at the beginning of `styles.css` to restyle every page. Keep styles/scripts in their files: the security policy blocks inline styles/scripts.

Display stage labels are in `ui.js`; stored values in `CrmOptions.java`. A new stored stage also needs a SQL constraint change and defined business transitions. An option alone does not define behavior.

For a new page, add a view function, an entry in `views`, and a navigation link in `index.html`. Reuse card/table/empty-state helpers. Move growing views into separate JavaScript modules later; the shared helpers stay the same.

Current lists/search/reports load permitted records into the browser. This suits a small student demonstration. Large datasets would need server-side pagination/search/aggregation.

## Consistency rules

- MySQL `BIGINT` IDs map to Java `Long`.
- MySQL `DECIMAL(10,2)` fees map to Java `BigDecimal`.
- Conversion locks the enquiry row. One transaction inserts one admission, updates the course/stage, and closes pending follow-ups.
- Admission fees are snapshots; course fee edits do not rewrite receipts.
- Counselors access presently assigned enquiries; reassignment changes access immediately. Historical activity authors remain recorded.
- Follow-up input is local IST. JVM/JDBC sessions use IST.
- Returned user models contain no password hash. Standard Java PBKDF2 uses a random salt and 600,000 iterations.
- Server session determines role and activity author.
- Dynamic HTML values are escaped before display.
- Credentials, database files, generated output, and screenshots stay outside Git.

## Syllabus mapping

| Implementation | Syllabus topic |
| --- | --- |
| Models/helpers/collections/exceptions | Core Java/OOP, page 2 |
| Layers and constructor injection | Layered applications/Spring Boot/DI, page 3 |
| SQL, JdbcTemplate, DAO | Spring JDBC/DAO, page 3; SQL/MySQL, page 2 |
| Relationships/uniqueness/transactions | MySQL/normalization, page 2 |
| Session identity/filter | Servlets, page 3; supporting Servlet APIs |
| Shared UI and browser interactions | HTML/CSS/JavaScript, page 2 |
| Maven/testing/Git | Pages 6/4 |

PBKDF2 hashing and CSRF checks are necessary supporting details, not explicitly named course subjects. Required HTTP/JSON dependencies come from Spring Boot. No optional security/ORM/UI/test framework was added.

Version reference: [Spring Boot 3.5 requirements](https://docs.spring.io/spring-boot/3.5/system-requirements.html). This project uses 3.5.16, Java release 17, and the installed JDK 21 for local build/run.
