# Aaryaa Tugaonkar — FBS coursework work

A running log of BCA/B.Sc. Computer Science coursework — lab programs, assignments, and one in-progress capstone — kept in a single working repo rather than split across course folders elsewhere.

🔗 **Styled overview page:** [live link here once GitHub Pages is enabled]

> Older coursework keeps compiled output beside source. The rebuilt Nexaanova Task 3 tracks source, setup instructions, and verification; generated build output, local databases, and credentials are excluded.

## What's in here

| Folder | Subject | What it covers |
|---|---|---|
| [`C Programming/`](./C%20Programming) | C | 10 assignment sets (127 `.c` programs), plus Lab work, a Project, and a Test set |
| [`Java/`](./Java) | Java | C→Java bridge exercises, dated classwork (inheritance, polymorphism, abstract classes, then data structures), and package practice |
| [`MySQL/`](./MySQL) | MySQL | Query labs, with a captured web-store page used as a reference case study |
| [`OOP/`](./OOP) | OOP theory | Standalone write-ups on the four pillars — object, abstraction, encapsulation, inheritance, polymorphism |
| [`WP/`](./WP) | Web programming | Axios fetch practice against a live products API |
| [`NexaNovaa/`](./NexaNovaa) | Capstone | Student admission CRM — see below |

## Java classwork, in order

The `Java/ClassWork` folder is dated, so it doubles as a syllabus trail:

1. Apr 22 — Inheritance test
2. Apr 24 — Inheritance, `Employee` hierarchy
3. Apr 27 — Polymorphism, `Employee`
4. Apr 28 — `Object` class
5. Apr 29 — Polymorphism, abstract classes & methods
6. Jun 01 — Data structures: stack
7. Jun 02 — Data structures: queue
8. Jun 03 — Data structures: circular queue
9. Jun 04 — Data structures: singly linked list
10. Jun 08 — Data structures: doubly linked list
11. Jun 09 — Search techniques
12. Jun 10 — Sorting techniques

`Java/Assignments` separately builds out an `Employee` → `HR` / `Admin` / `SalesManager` inheritance hierarchy used across multiple tasks.

## NexaNova — student admission CRM

Updated **8 October 2026**: Task 3 implements a complete local enquiry-to-admission workflow.

- **Task 1:** requirements, roles, workflows, and diagrams, preserved as design groundwork.
- **Task 2:** 17 static prototype pages, preserved as UI references.
- **Task 3:** Java 17+, Spring Boot 3.5.16, explicit JDBC/DAO SQL, MySQL, servlet sessions, and shared HTML/CSS/JavaScript.
- **Working:** real login/access checks, enquiries/assignment, calls, IST follow-ups, transactional admission, exact balances, receipts, team/courses, real counts, and basic reports.
- **Editing:** shared CSS/forms and clear controller/service/DAO layers with a field/stage edit guide.
- **Verified:** 17 Java checks, 29 API groups, 23 browser groups, seven restart/persistence checks, and responsive previews.
- **Pending:** CSV import/export, installment ledger, date-range reports, password change/reset, Trainer/Student portals.

[Project journey](NexaNovaa/README.md) · [Improvements](NexaNovaa/3rd%20Task/docs/improvements.md) · [Edit guide](NexaNovaa/3rd%20Task/docs/architecture.md) · [Work log](NexaNovaa/log%20of%20nexaa/README.md)

![Nexaanova overview with fictional sample records](NexaNovaa/3rd%20Task/docs/screenshots/overview-desktop.png)

### Running Task 3 locally on Windows

```powershell
cd 'NexaNovaa/3rd Task'
.\scripts\run-local.ps1
```

Install JDK 17+ and MySQL Server first; see [Task 3 setup](NexaNovaa/3rd%20Task/README.md) for installation paths and manual configuration. The wrapper handles Maven. The default helper uses a separate localhost database on port 3307; generated credentials stay in the ignored .local folder.

The original Task 3 remains in Git history. Tasks 1/2 and other coursework are preserved.

## Notes for anyone browsing this

- Folder names mix manual dating conventions (`June_4_DS-SLL` and `June_04_DS-SLL` both exist) — that's coursework drift, not a typo to "fix."
- Built `.class` / `.exe` files sitting next to source are intentional leftovers from compiling locally, not accidental commits.
- This is a personal academic archive — code quality and structure reflect a student working through concepts in real time, including some deliberately preserved lecture errors in early Java files.

---
*K.T. Patil College of Computer Science, Dharashiv (Dr. BAMU)*
