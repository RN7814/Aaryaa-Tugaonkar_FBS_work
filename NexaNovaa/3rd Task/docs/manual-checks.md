# Acceptance checks

Use the separate practice database and fictional test records.

1. Anonymous API access/wrong passwords fail; login renews session; logout blocks later access.
2. Admin creates a course/user/enquiry, assigns it, and sees saved values after reload.
3. Duplicate Indian phone representations fail clearly.
4. Counselor lists exclude others' enquiries. Direct unauthorized reads/writes fail.
5. Reassignment immediately moves access to the new Counselor.
6. Record call outcomes; check matching stages and retained history.
7. Schedule future IST follow-ups; reject past times. Complete once; repeat fails.
8. Closure clears pending follow-ups; reopen explicitly before further activity.
9. Negative fees, excessive received amounts, and fractional paise fail without an admission.
10. Conversion produces exact balance, admitted stage, and closed pending follow-ups.
11. Two simultaneous admission requests produce exactly one admission.
12. Admitted enquiry edits/calls/repeat conversion fail.
13. Course fee changes preserve earlier admission fees/receipts.
14. Deactivating a signed-in Counselor blocks their next request.
15. Manager reads team reports but cannot mutate data/manage accounts; logout works.
16. Missing/wrong session form tokens block writes.
17. HTML-looking notes display as text.
18. Print receipt hides screen controls. Check mobile layout, labels, keyboard focus, and dialogs.
19. Restart local app/database and verify persistence without duplicate sample data.

Run the ordinary Java `CoreChecks` main method explicitly after Maven builds. It checks normalization/invalid values/exact money/password handling without a test framework.

Actual results are recorded in [the selected project log](../../log%20of%20nexaa/README.md), with screenshots in [screenshots/](screenshots/). The workspace retains the full audit/evidence folder. This checklist is a repeatable procedure; future changes require their own verification.
