package com.nexaanova.crm.config;

import com.nexaanova.crm.dao.*;
import com.nexaanova.crm.model.*;
import com.nexaanova.crm.util.PasswordHelper;
import com.nexaanova.crm.util.Validation;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

// Runs only for an empty database. Restarting never overwrites student records.
@Component
public class InitialData implements ApplicationRunner {
    private final UserDao users;
    private final CourseDao courses;
    private final LeadDao leads;
    private final ActivityDao activity;
    private final AdmissionDao admissions;
    @Value("${app.admin-email}") private String adminEmail;
    @Value("${app.admin-password}") private String adminPassword;
    @Value("${app.sample-data:false}") private boolean sampleData;
    @Value("${app.sample-password:}") private String samplePassword;

    public InitialData(UserDao users, CourseDao courses, LeadDao leads, ActivityDao activity, AdmissionDao admissions) {
        this.users = users; this.courses = courses; this.leads = leads;
        this.activity = activity; this.admissions = admissions;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        if (users.count() > 0) { return; }
        Validation.password(adminPassword);
        account("Administrator", Validation.email(adminEmail, true), "ADMIN", adminPassword);
        if (!sampleData) { return; }
        Validation.password(samplePassword);
        long counselor = account("Aditi Shah", "counselor@nexaanova.local", "COUNSELOR", samplePassword);
        long second = account("Rohan Mehta", "second.counselor@nexaanova.local", "COUNSELOR", samplePassword);
        account("Team Manager", "manager@nexaanova.local", "MANAGER", samplePassword);
        long java = course("Java Full Stack", "6 months", "65000.00");
        long core = course("Core Java", "3 months", "24000.00");
        long sql = course("MySQL Essentials", "6 weeks", "12000.00");
        String[] names = {"Ananya Deshmukh", "Kabir Patil", "Ishita Rao", "Arjun Kulkarni", "Meera Joshi", "Dev Shah", "Sara Khan", "Vihaan Nair"};
        String[] stages = {"OPEN", "FOLLOW_UP", "INTERESTED", "CNR", "CONVERTED", "CALL_BACK", "CLOSED", "OPEN"};
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
        for (int i = 0; i < names.length; i++) {
            Lead lead = new Lead();
            lead.setName(names[i]);
            lead.setPhone("+91900000000" + i);
            lead.setCity(i % 2 == 0 ? "Pune" : "Mumbai");
            lead.setCollege("Sample College");
            lead.setQualification("Graduate");
            lead.setSource(i % 2 == 0 ? "Website" : "Referral");
            lead.setRemarks("Sample enquiry for practising the CRM workflow.");
            lead.setCourseId(i % 3 == 0 ? core : i % 3 == 1 ? java : sql);
            lead.setCounselorId(i == 7 ? null : i % 2 == 0 ? counselor : second);
            long id = leads.create(lead);
            leads.setStage(id, stages[i]);
            if (i == 1 || i == 2 || i == 5) {
                Followup followup = new Followup();
                followup.setLeadId(id); followup.setUserId(lead.getCounselorId());
                followup.setDueAt(i == 1 ? now.minusHours(2) : now.plusDays(1));
                followup.setNotes(i == 1 ? "Discuss the next batch and course timings." : "Check the student’s preferred batch.");
                activity.addFollowup(followup);
            }
            if (i == 2) {
                CallRecord call = new CallRecord();
                call.setLeadId(id); call.setUserId(counselor); call.setOutcome("INTERESTED");
                call.setNotes("Student asked about evening batches.");
                activity.addCall(call);
            }
            if (i == 4) {
                Admission admission = new Admission();
                admission.setLeadId(id); admission.setCourseId(java); admission.setUserId(counselor);
                admission.setTotalFees(new BigDecimal("65000.00"));
                admission.setFeesPaid(new BigDecimal("25000.00")); admission.setPaymentMode("Online");
                admissions.create(admission); leads.setCourse(id, java);
            }
        }
    }

    private long account(String name, String email, String role, String password) {
        UserAccount user = new UserAccount();
        user.setName(name); user.setEmail(email); user.setRole(role);
        return users.create(user, PasswordHelper.hash(password));
    }

    private long course(String name, String duration, String fees) {
        Course course = new Course();
        course.setName(name); course.setDuration(duration); course.setFees(new BigDecimal(fees));
        return courses.create(course);
    }
}
