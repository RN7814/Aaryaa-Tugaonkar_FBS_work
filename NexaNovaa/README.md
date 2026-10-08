# Nexaanova — Java admission CRM

Task 3 now contains a working local CRM built with Java, Spring Boot, JDBC/DAO, MySQL, and HTML/CSS/JavaScript.

![Nexaanova overview with fictional sample records](3rd%20Task/docs/screenshots/overview-desktop.png)

## Project journey

| Stage | What it contains | Current status |
| --- | --- | --- |
| [Task 1](1st%20Task/) | Requirements, roles, workflow and system diagrams | Preserved design groundwork |
| [Task 2](2nd%20Task/) | 17 static admin/counselor/login prototype pages | Preserved UI reference |
| [Task 3](3rd%20Task/) | Java/MySQL application with shared responsive frontend | First complete workflow implemented and checked |
| [Current Workspace](Current%20Workspace/) | Original copy of Task 2 pages | Preserved reference |

## What works

Admin creates accounts/courses and adds/assigns enquiries. Counselors work with assigned enquiries, record calls, schedule/complete follow-ups, and confirm admissions. Managers review team data without editing it.

The app saves records to MySQL, validates duplicate phones and fees, calculates admission balances, prints initial-payment receipts, and shows real counts/reports. Shared styles/forms and explicit Java layers make later changes easier.

## Start and explore

Read [Task 3 setup](3rd%20Task/README.md), [improvements from the original work](3rd%20Task/docs/improvements.md), and [the edit guide](3rd%20Task/docs/architecture.md).

On Windows with JDK 17+ and MySQL Server installed:

```powershell
cd 'NexaNovaa/3rd Task'
.\scripts\run-local.ps1
```

The included Maven wrapper handles the build. The default helper starts a separate local database on port 3307; account passwords are generated locally in the ignored .local folder.

## Verification and next steps

On 8 October 2026: 17 plain Java checks, 29 API acceptance groups, 23 browser acceptance groups, and seven restart/persistence checks passed. [Selected work log and evidence](log%20of%20nexaa/README.md).

Pending: CSV import/export, installments, date-range reports, password change/reset, Trainer/Student portals. The working core remains a local practice application.

The original Task 3 is retained in Git history. Its active folder was replaced with the fresh source; generated build/database files and the bundled Maven distribution are excluded. Tasks 1/2 and other coursework remain unchanged.
