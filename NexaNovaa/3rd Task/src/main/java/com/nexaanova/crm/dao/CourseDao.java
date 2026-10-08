package com.nexaanova.crm.dao;

import com.nexaanova.crm.model.Course;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CourseDao {
    private final JdbcTemplate jdbc;
    private final RowMapper<Course> mapper = (rs, row) -> {
        Course course = new Course();
        course.setId(rs.getLong("id"));
        course.setName(rs.getString("name"));
        course.setDuration(rs.getString("duration"));
        course.setFees(rs.getBigDecimal("fees"));
        course.setActive(rs.getBoolean("active"));
        return course;
    };

    public CourseDao(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    public List<Course> findAll() { return jdbc.query("SELECT * FROM courses ORDER BY name", mapper); }

    public Course findById(long id) {
        List<Course> courses = jdbc.query("SELECT * FROM courses WHERE id=?", mapper, id);
        return courses.isEmpty() ? null : courses.get(0);
    }

    public long create(Course course) {
        return DaoHelper.insert(jdbc, "INSERT INTO courses(name,duration,fees) VALUES(?,?,?)", course.getName(), course.getDuration(), course.getFees());
    }

    public void update(Course course) {
        jdbc.update("UPDATE courses SET name=?,duration=?,fees=?,active=? WHERE id=?",
                course.getName(), course.getDuration(), course.getFees(), course.getActive(), course.getId());
    }
}
