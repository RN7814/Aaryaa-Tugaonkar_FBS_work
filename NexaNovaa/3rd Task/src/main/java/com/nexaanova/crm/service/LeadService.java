package com.nexaanova.crm.service;

import com.nexaanova.crm.dao.ActivityDao;
import com.nexaanova.crm.dao.AdmissionDao;
import com.nexaanova.crm.dao.CourseDao;
import com.nexaanova.crm.dao.LeadDao;
import com.nexaanova.crm.dao.UserDao;
import com.nexaanova.crm.model.*;
import com.nexaanova.crm.util.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LeadService {
    private final LeadDao leads;
    private final UserDao users;
    private final CourseDao courses;
    private final ActivityDao activity;
    private final AdmissionDao admissions;

    public LeadService(LeadDao leads, UserDao users, CourseDao courses, ActivityDao activity, AdmissionDao admissions) {
        this.leads = leads;
        this.users = users;
        this.courses = courses;
        this.activity = activity;
        this.admissions = admissions;
    }

    public List<Lead> list(UserAccount user) { return leads.findAll(user); }

    public Lead accessible(long id, UserAccount user) {
        Lead lead = leads.findById(id);
        if (lead == null) { throw new ApiException(404, "Lead was not found."); }
        if ("COUNSELOR".equals(user.getRole()) && !user.getId().equals(lead.getCounselorId())) {
            throw new ApiException(403, "This lead is not assigned to you.");
        }
        return lead;
    }

    public Map<String, Object> detail(long id, UserAccount user) {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("lead", accessible(id, user));
        data.put("calls", activity.calls(id));
        data.put("followups", activity.forLead(id));
        data.put("admission", admissions.forLead(id));
        return data;
    }

    @Transactional
    public Lead create(Lead lead, UserAccount user) {
        Access.writer(user);
        validate(lead);
        if ("COUNSELOR".equals(user.getRole())) { lead.setCounselorId(user.getId()); }
        counselor(lead.getCounselorId());
        return leads.findById(leads.create(lead));
    }

    @Transactional
    public Lead update(long id, Lead lead, UserAccount user) {
        Access.writer(user);
        leads.lock(id);
        Lead current = accessible(id, user);
        if ("CONVERTED".equals(current.getStage())) { throw new ApiException(409, "An admitted lead cannot be edited."); }
        validate(lead);
        lead.setId(id);
        leads.update(lead);
        return leads.findById(id);
    }

    @Transactional
    public Lead assign(long id, Long counselorId, UserAccount user) {
        Access.admin(user);
        leads.lock(id);
        Lead lead = accessible(id, user);
        if ("CONVERTED".equals(lead.getStage())) { throw new ApiException(409, "An admitted lead cannot be reassigned."); }
        counselor(counselorId);
        leads.assign(id, counselorId);
        return leads.findById(id);
    }

    @Transactional
    public Lead stage(long id, String stage, UserAccount user) {
        Access.writer(user);
        leads.lock(id);
        Lead lead = accessible(id, user);
        Validation.choice(stage, "lead stage", CrmOptions.STAGES);
        if ("CONVERTED".equals(stage) || "CONVERTED".equals(lead.getStage())) {
            throw new ApiException(409, "Use the admission form to convert a lead.");
        }
        leads.setStage(id, stage);
        if ("CLOSED".equals(stage)) { activity.closePending(id); }
        return leads.findById(id);
    }

    @Transactional
    public Map<String, Object> recordCall(long id, CallRecord call, UserAccount user) {
        writable(id, user);
        call.setLeadId(id);
        call.setUserId(user.getId());
        call.setOutcome(Validation.choice(call.getOutcome(), "call outcome", CrmOptions.OUTCOMES));
        call.setNotes(Validation.text(call.getNotes(), "Call notes", 2000, true));
        activity.addCall(call);
        String stage = "INTERESTED";
        if ("NO_ANSWER".equals(call.getOutcome())) { stage = "CNR"; }
        if ("CALL_LATER".equals(call.getOutcome())) { stage = "CALL_BACK"; }
        if ("NOT_INTERESTED".equals(call.getOutcome())) { stage = "CLOSED"; activity.closePending(id); }
        leads.setStage(id, stage);
        return detail(id, user);
    }

    @Transactional
    public Map<String, Object> schedule(long id, Followup followup, UserAccount user) {
        writable(id, user);
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
        if (followup.getDueAt() == null || followup.getDueAt().isBefore(now)) {
            throw new ApiException(400, "Choose a future follow-up date and time (IST).");
        }
        followup.setNotes(Validation.text(followup.getNotes(), "Follow-up notes", 2000, true));
        followup.setLeadId(id);
        followup.setUserId(user.getId());
        activity.addFollowup(followup);
        leads.setStage(id, "FOLLOW_UP");
        return detail(id, user);
    }

    @Transactional
    public void completeFollowup(long id, UserAccount user) {
        Access.writer(user);
        Followup followup = activity.findById(id);
        if (followup == null) { throw new ApiException(404, "Follow-up was not found."); }
        writable(followup.getLeadId(), user);
        if (!"PENDING".equals(followup.getStatus())) { throw new ApiException(409, "This follow-up is already completed or closed."); }
        if (activity.complete(id) == 0) { throw new ApiException(409, "This follow-up was completed by another request."); }
    }

    @Transactional
    public Map<String, Object> admit(long id, Admission admission, UserAccount user) {
        writable(id, user);
        if (admissions.forLead(id) != null) { throw new ApiException(409, "This lead already has an admission."); }
        activeCourse(admission.getCourseId());
        admission.setTotalFees(Validation.money(admission.getTotalFees(), "Total fees", false));
        admission.setFeesPaid(Validation.money(admission.getFeesPaid(), "Fees paid", true));
        if (admission.getFeesPaid().compareTo(admission.getTotalFees()) > 0) { throw new ApiException(400, "Fees paid cannot exceed total fees."); }
        admission.setPaymentMode(Validation.choice(admission.getPaymentMode(), "payment mode", CrmOptions.PAYMENTS));
        admission.setLeadId(id);
        admission.setUserId(user.getId());
        admissions.create(admission);
        leads.setCourse(id, admission.getCourseId());
        leads.setStage(id, "CONVERTED");
        activity.closePending(id);
        return detail(id, user);
    }

    private Lead writable(long id, UserAccount user) {
        Access.writer(user);
        leads.lock(id);
        Lead lead = accessible(id, user);
        if ("CONVERTED".equals(lead.getStage()) || "CLOSED".equals(lead.getStage())) {
            throw new ApiException(409, "This lead is already admitted or closed.");
        }
        return lead;
    }

    private void validate(Lead lead) {
        lead.setName(Validation.text(lead.getName(), "Student name", 100, true));
        lead.setPhone(Validation.phone(lead.getPhone()));
        lead.setEmail(Validation.email(lead.getEmail(), false));
        lead.setCity(Validation.text(lead.getCity(), "City", 100, false));
        lead.setCollege(Validation.text(lead.getCollege(), "College", 150, false));
        lead.setQualification(Validation.text(lead.getQualification(), "Qualification", 80, false));
        lead.setRemarks(Validation.text(lead.getRemarks(), "Remarks", 2000, false));
        Validation.choice(lead.getSource(), "source", CrmOptions.SOURCES);
        if (lead.getCourseId() != null) { activeCourse(lead.getCourseId()); }
    }

    private void activeCourse(Long id) {
        Course course = id == null ? null : courses.findById(id);
        if (course == null || !course.getActive()) { throw new ApiException(400, "Choose an active course."); }
    }

    private void counselor(Long id) {
        if (id == null) { return; }
        UserAccount user = users.findById(id);
        if (user == null || !user.getActive() || !"COUNSELOR".equals(user.getRole())) {
            throw new ApiException(400, "Choose an active Counselor.");
        }
    }
}
