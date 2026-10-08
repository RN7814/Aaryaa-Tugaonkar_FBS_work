package com.nexaanova.crm.controller;

import com.nexaanova.crm.dao.*;
import com.nexaanova.crm.model.*;
import com.nexaanova.crm.service.*;
import com.nexaanova.crm.util.*;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

// Controllers translate browser requests; services own the business rules.
@RestController
@RequestMapping("/api")
public class CrmController {
    private final LeadService leads;
    private final UserService userService;
    private final CourseService courseService;
    private final UserDao users;
    private final CourseDao courses;
    private final ActivityDao activity;
    private final AdmissionDao admissions;
    @Value("${app.sample-data:false}") private boolean sampleData;

    public CrmController(LeadService leads, UserService userService, CourseService courseService,
                         UserDao users, CourseDao courses, ActivityDao activity, AdmissionDao admissions) {
        this.leads = leads; this.userService = userService; this.courseService = courseService;
        this.users = users; this.courses = courses; this.activity = activity; this.admissions = admissions;
    }

    @GetMapping("/options")
    public Map<String, Object> options() {
        List<UserAccount> counselors = new ArrayList<>();
        for (UserAccount user : users.findAll()) {
            if (user.getActive() && "COUNSELOR".equals(user.getRole())) { counselors.add(user); }
        }
        return Map.of("stages", CrmOptions.STAGES, "sources", CrmOptions.SOURCES, "outcomes", CrmOptions.OUTCOMES,
                "payments", CrmOptions.PAYMENTS, "courses", courses.findAll(), "counselors", counselors, "sampleData", sampleData);
    }

    @GetMapping("/leads") public List<Lead> list(HttpServletRequest request) { return leads.list(Access.user(request)); }
    @GetMapping("/leads/{id}") public Map<String, Object> detail(@PathVariable long id, HttpServletRequest request) { return leads.detail(id, Access.user(request)); }
    @PostMapping("/leads") public Lead create(@RequestBody Lead lead, HttpServletRequest request) { return leads.create(lead, Access.user(request)); }
    @PutMapping("/leads/{id}") public Lead update(@PathVariable long id, @RequestBody Lead lead, HttpServletRequest request) { return leads.update(id, lead, Access.user(request)); }
    @PutMapping("/leads/{id}/assignment") public Lead assign(@PathVariable long id, @RequestBody Lead lead, HttpServletRequest request) { return leads.assign(id, lead.getCounselorId(), Access.user(request)); }
    @PutMapping("/leads/{id}/stage") public Lead stage(@PathVariable long id, @RequestBody Lead lead, HttpServletRequest request) { return leads.stage(id, lead.getStage(), Access.user(request)); }
    @PostMapping("/leads/{id}/calls") public Map<String, Object> call(@PathVariable long id, @RequestBody CallRecord call, HttpServletRequest request) { return leads.recordCall(id, call, Access.user(request)); }
    @PostMapping("/leads/{id}/followups") public Map<String, Object> schedule(@PathVariable long id, @RequestBody Followup followup, HttpServletRequest request) { return leads.schedule(id, followup, Access.user(request)); }
    @PostMapping("/leads/{id}/admission") public Map<String, Object> admit(@PathVariable long id, @RequestBody Admission admission, HttpServletRequest request) { return leads.admit(id, admission, Access.user(request)); }
    @GetMapping("/followups") public List<Followup> followups(HttpServletRequest request) { return activity.followups(Access.user(request)); }
    @PutMapping("/followups/{id}/complete") public Map<String, String> complete(@PathVariable long id, HttpServletRequest request) {
        leads.completeFollowup(id, Access.user(request)); return Map.of("message", "Follow-up completed.");
    }
    @GetMapping("/admissions") public List<Admission> admissions(HttpServletRequest request) { return admissions.findAll(Access.user(request)); }

    @GetMapping("/users") public List<UserAccount> users(HttpServletRequest request) { Access.admin(Access.user(request)); return users.findAll(); }
    @PostMapping("/users") public UserAccount createUser(@RequestBody UserRequest user, HttpServletRequest request) { return userService.create(user, Access.user(request)); }
    @PutMapping("/users/{id}/active") public UserAccount active(@PathVariable long id, @RequestBody UserAccount user, HttpServletRequest request) { return userService.setActive(id, user.getActive(), Access.user(request)); }
    @GetMapping("/courses") public List<Course> courses(HttpServletRequest request) { Access.admin(Access.user(request)); return courses.findAll(); }
    @PostMapping("/courses") public Course createCourse(@RequestBody Course course, HttpServletRequest request) { return courseService.save(course, null, Access.user(request)); }
    @PutMapping("/courses/{id}") public Course updateCourse(@PathVariable long id, @RequestBody Course course, HttpServletRequest request) { return courseService.save(course, id, Access.user(request)); }
}
