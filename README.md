# Aaryaa Tugaonkar — FBS coursework work

A running log of BCA/B.Sc. Computer Science coursework — lab programs, assignments, and one in-progress capstone — kept in a single working repo rather than split across course folders elsewhere.

🔗 **Styled overview page:** [live link here once GitHub Pages is enabled]

> This repo is a working lab folder, not a curated showcase — compiled output (`.class`, `.exe`, `.jar`) is committed alongside source throughout, and folders are organized the way coursework actually arrived, week by week.

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

The repo's largest standing build, tracked task by task rather than dropped in as a finished project.

- **Task 1 / Task 2** — early scaffolding and design groundwork
- **Task 3** — a real Spring Boot service, currently the active workspace:
  - **Backend:** Spring Boot 3.2.0, layered into `model` / `dto` / `config` / `repository` / `service` / `controller`
  - **Auth:** Spring Security with JWT (`jjwt`)
  - **Persistence:** Spring Data JPA + MySQL connector
  - **Frontend:** static role-based dashboards (`admin-dashboard.html`, `counselor-dashboard.html`, `trainer-dashboard.html`, plus reports, leads, and follow-up pages)
  - Ships its own Maven distribution and `target/` build output alongside source — it's a buildable workspace, not a snapshot

### Running Task 3 locally

```bash
cd "NexaNovaa/3rd Task"
./mvnw spring-boot:run     # or use the bundled apache-maven-3.9.9 if mvnw isn't set up
```

You'll need a local MySQL instance and matching connection details in `src/main/resources/application.properties` (not included here — set your own credentials).

## Notes for anyone browsing this

- Folder names mix manual dating conventions (`June_4_DS-SLL` and `June_04_DS-SLL` both exist) — that's coursework drift, not a typo to "fix."
- Built `.class` / `.exe` files sitting next to source are intentional leftovers from compiling locally, not accidental commits.
- This is a personal academic archive — code quality and structure reflect a student working through concepts in real time, including some deliberately preserved lecture errors in early Java files.

---
*K.T. Patil College of Computer Science, Dharashiv (Dr. BAMU)*
