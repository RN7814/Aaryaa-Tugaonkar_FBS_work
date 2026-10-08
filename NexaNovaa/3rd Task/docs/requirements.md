# First version requirements

## Access

| Action | Admin | Counselor | Manager |
| --- | --- | --- | --- |
| Review enquiries/activity/admissions | All | Assigned only | All |
| Create/update enquiries | Yes | Own/assigned | No |
| Assign/reassign/unassign | Yes | No | No |
| Calls/follow-ups/admission | Yes | Assigned only | No |
| Accounts/courses | Yes | No | No |
| Reports | Team | Own records | Team |

Inactive accounts lose existing-session access too. An Admin cannot deactivate their own account.

## Stages

| Stored value | Display / mockup connection | Behavior |
| --- | --- | --- |
| OPEN | New enquiry / Open | Initial saved stage |
| CNR | No response / CNR | No-answer call; available for another attempt |
| CALL_BACK | Call back | Student asks for a later call |
| INTERESTED | Interested / Stage 2 | Interested call outcome |
| FOLLOW_UP | Follow-up / Stage 2.5 | Follow-up scheduled |
| CONVERTED | Admitted / Done | Admission only; enquiry locked |
| CLOSED | Closed | Not interested/manually closed; pending follow-ups closed |

No automatic CNR retry limit is assumed. Choose another attempt or close manually. Closed enquiries can reopen through **Change stage**. Admitted enquiries cannot reopen/reassign/edit/convert again. Completing a follow-up marks its task done; it does not guess a call outcome or alter the enquiry stage.

## Fields and admission

- Name, valid phone, and listed source are required.
- Course/counselor can be undecided/unassigned on an Admin enquiry.
- Counselors' new enquiries are assigned to themselves.
- Indian local phone formats normalize to +91; SQL rejects duplicate phones. Foreign numbers should include their + country code.
- Optional emails must be valid when supplied.
- Assignment requires an active Counselor; admission requires an active course.
- Call/follow-up notes are required. Due time must be future IST.
- Agreed fees must be positive. Initial received amount can be zero, cannot exceed agreed fees, and supports two decimal places.
- Balance = agreed fees minus received amount.
- One admission per enquiry; conversion closes pending follow-ups.
- This version records the initial payment only, without installments.

## Pending

CSV import/export, date-range reports, installments, password change/reset, Trainer/Student portals. Extend the working core one feature at a time.
