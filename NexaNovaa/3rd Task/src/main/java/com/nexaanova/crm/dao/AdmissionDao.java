package com.nexaanova.crm.dao;

import com.nexaanova.crm.model.Admission;
import com.nexaanova.crm.model.UserAccount;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AdmissionDao {
    private final JdbcTemplate jdbc;
    private static final String SELECT = "SELECT a.*,l.name AS lead_name,c.name AS course_name FROM admissions a JOIN leads l ON l.id=a.lead_id JOIN courses c ON c.id=a.course_id ";
    private final RowMapper<Admission> mapper = (rs, row) -> {
        Admission admission = new Admission();
        admission.setId(rs.getLong("id"));
        admission.setLeadId(rs.getLong("lead_id"));
        admission.setLeadName(rs.getString("lead_name"));
        admission.setCourseId(rs.getLong("course_id"));
        admission.setCourseName(rs.getString("course_name"));
        admission.setUserId(rs.getLong("user_id"));
        admission.setTotalFees(rs.getBigDecimal("total_fees"));
        admission.setFeesPaid(rs.getBigDecimal("fees_paid"));
        admission.setPaymentMode(rs.getString("payment_mode"));
        admission.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
        return admission;
    };

    public AdmissionDao(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    public List<Admission> findAll(UserAccount user) {
        if ("COUNSELOR".equals(user.getRole())) {
            return jdbc.query(SELECT + "WHERE l.counselor_id=? ORDER BY a.id DESC", mapper, user.getId());
        }
        return jdbc.query(SELECT + "ORDER BY a.id DESC", mapper);
    }

    public Admission forLead(long leadId) {
        List<Admission> admissions = jdbc.query(SELECT + "WHERE a.lead_id=?", mapper, leadId);
        return admissions.isEmpty() ? null : admissions.get(0);
    }

    public long create(Admission admission) {
        return DaoHelper.insert(jdbc, "INSERT INTO admissions(lead_id,course_id,user_id,total_fees,fees_paid,payment_mode) VALUES(?,?,?,?,?,?)",
                admission.getLeadId(), admission.getCourseId(), admission.getUserId(), admission.getTotalFees(), admission.getFeesPaid(), admission.getPaymentMode());
    }
}
