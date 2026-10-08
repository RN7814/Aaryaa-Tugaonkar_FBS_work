package com.nexaanova.crm.service;

import com.nexaanova.crm.dao.CourseDao;
import com.nexaanova.crm.model.Course;
import com.nexaanova.crm.model.UserAccount;
import com.nexaanova.crm.util.ApiException;
import com.nexaanova.crm.util.Validation;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseDao courses;

    public CourseService(CourseDao courses) { this.courses = courses; }

    public Course save(Course course, Long id, UserAccount actor) {
        Access.admin(actor);
        course.setName(Validation.text(course.getName(), "Course name", 100, true));
        course.setDuration(Validation.text(course.getDuration(), "Duration", 60, true));
        course.setFees(Validation.money(course.getFees(), "Course fees", false));
        if (id == null) {
            id = courses.create(course);
        } else {
            if (courses.findById(id) == null) { throw new ApiException(404, "Course was not found."); }
            course.setId(id);
            courses.update(course);
        }
        return courses.findById(id);
    }
}
