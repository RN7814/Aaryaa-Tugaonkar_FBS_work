package com.nexaanova.crm.dao;

import com.nexaanova.crm.model.CallRecord;
import com.nexaanova.crm.model.Followup;
import com.nexaanova.crm.model.UserAccount;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ActivityDao {
    private final JdbcTemplate jdbc;
    private static final String FOLLOWUPS = "SELECT f.*, l.name AS lead_name, l.phone, u.name AS user_name FROM followups f JOIN leads l ON l.id=f.lead_id JOIN users u ON u.id=f.user_id ";
    private final RowMapper<Followup> followupMapper = (rs, row) -> {
        Followup followup = new Followup();
        followup.setId(rs.getLong("id"));
        followup.setLeadId(rs.getLong("lead_id"));
        followup.setLeadName(rs.getString("lead_name"));
        followup.setPhone(rs.getString("phone"));
        followup.setUserId(rs.getLong("user_id"));
        followup.setUserName(rs.getString("user_name"));
        followup.setDueAt(rs.getObject("due_at", LocalDateTime.class));
        followup.setNotes(rs.getString("notes"));
        followup.setStatus(rs.getString("status"));
        return followup;
    };

    public ActivityDao(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    public List<CallRecord> calls(long leadId) {
        return jdbc.query("SELECT c.*, u.name AS user_name FROM calls c JOIN users u ON u.id=c.user_id WHERE c.lead_id=? ORDER BY c.id DESC", (rs, row) -> {
            CallRecord call = new CallRecord();
            call.setId(rs.getLong("id"));
            call.setLeadId(rs.getLong("lead_id"));
            call.setUserId(rs.getLong("user_id"));
            call.setUserName(rs.getString("user_name"));
            call.setOutcome(rs.getString("outcome"));
            call.setNotes(rs.getString("notes"));
            call.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
            return call;
        }, leadId);
    }

    public long addCall(CallRecord call) {
        return DaoHelper.insert(jdbc, "INSERT INTO calls(lead_id,user_id,outcome,notes) VALUES(?,?,?,?)", call.getLeadId(), call.getUserId(), call.getOutcome(), call.getNotes());
    }

    public List<Followup> followups(UserAccount user) {
        if ("COUNSELOR".equals(user.getRole())) {
            return jdbc.query(FOLLOWUPS + "WHERE l.counselor_id=? ORDER BY f.status='PENDING' DESC,f.due_at", followupMapper, user.getId());
        }
        return jdbc.query(FOLLOWUPS + "ORDER BY f.status='PENDING' DESC,f.due_at", followupMapper);
    }

    public List<Followup> forLead(long leadId) {
        return jdbc.query(FOLLOWUPS + "WHERE f.lead_id=? ORDER BY f.due_at DESC", followupMapper, leadId);
    }

    public Followup findById(long id) {
        List<Followup> records = jdbc.query(FOLLOWUPS + "WHERE f.id=?", followupMapper, id);
        return records.isEmpty() ? null : records.get(0);
    }

    public long addFollowup(Followup followup) {
        return DaoHelper.insert(jdbc, "INSERT INTO followups(lead_id,user_id,due_at,notes) VALUES(?,?,?,?)", followup.getLeadId(), followup.getUserId(), followup.getDueAt(), followup.getNotes());
    }

    public int complete(long id) { return jdbc.update("UPDATE followups SET status='DONE' WHERE id=? AND status='PENDING'", id); }
    public void closePending(long leadId) { jdbc.update("UPDATE followups SET status='CLOSED' WHERE lead_id=? AND status='PENDING'", leadId); }
}
