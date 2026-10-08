package com.nexaanova.crm.dao;

import com.nexaanova.crm.model.Lead;
import com.nexaanova.crm.model.UserAccount;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class LeadDao {
    private final JdbcTemplate jdbc;
    private static final String SELECT = "SELECT l.*, c.name AS course_name, u.name AS counselor_name FROM leads l LEFT JOIN courses c ON c.id=l.course_id LEFT JOIN users u ON u.id=l.counselor_id ";
    private final RowMapper<Lead> mapper = (rs, row) -> {
        Lead lead = new Lead();
        lead.setId(rs.getLong("id"));
        lead.setName(rs.getString("name"));
        lead.setPhone(rs.getString("phone"));
        lead.setEmail(rs.getString("email"));
        lead.setCity(rs.getString("city"));
        lead.setCollege(rs.getString("college"));
        lead.setQualification(rs.getString("qualification"));
        lead.setSource(rs.getString("source"));
        lead.setStage(rs.getString("stage"));
        lead.setRemarks(rs.getString("remarks"));
        lead.setCourseId(rs.getObject("course_id", Long.class));
        lead.setCourseName(rs.getString("course_name"));
        lead.setCounselorId(rs.getObject("counselor_id", Long.class));
        lead.setCounselorName(rs.getString("counselor_name"));
        lead.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
        return lead;
    };

    public LeadDao(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    public List<Lead> findAll(UserAccount user) {
        if ("COUNSELOR".equals(user.getRole())) {
            return jdbc.query(SELECT + "WHERE l.counselor_id=? ORDER BY l.id DESC", mapper, user.getId());
        }
        return jdbc.query(SELECT + "ORDER BY l.id DESC", mapper);
    }

    public Lead findById(long id) {
        List<Lead> leads = jdbc.query(SELECT + "WHERE l.id=?", mapper, id);
        return leads.isEmpty() ? null : leads.get(0);
    }

    // A row lock keeps admission conversion and call/follow-up changes consistent.
    public void lock(long id) {
        jdbc.queryForList("SELECT id FROM leads WHERE id=? FOR UPDATE", id);
    }

    public long create(Lead lead) {
        return DaoHelper.insert(jdbc, "INSERT INTO leads(name,phone,email,city,college,qualification,source,remarks,course_id,counselor_id) VALUES(?,?,?,?,?,?,?,?,?,?)",
                lead.getName(), lead.getPhone(), lead.getEmail(), lead.getCity(), lead.getCollege(), lead.getQualification(),
                lead.getSource(), lead.getRemarks(), lead.getCourseId(), lead.getCounselorId());
    }

    public void update(Lead lead) {
        jdbc.update("UPDATE leads SET name=?,phone=?,email=?,city=?,college=?,qualification=?,source=?,remarks=?,course_id=? WHERE id=?",
                lead.getName(), lead.getPhone(), lead.getEmail(), lead.getCity(), lead.getCollege(), lead.getQualification(),
                lead.getSource(), lead.getRemarks(), lead.getCourseId(), lead.getId());
    }

    public void assign(long id, Long counselorId) { jdbc.update("UPDATE leads SET counselor_id=? WHERE id=?", counselorId, id); }
    public void setStage(long id, String stage) { jdbc.update("UPDATE leads SET stage=? WHERE id=?", stage, id); }
    public void setCourse(long id, long courseId) { jdbc.update("UPDATE leads SET course_id=? WHERE id=?", courseId, id); }
}
