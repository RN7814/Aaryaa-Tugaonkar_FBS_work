package com.nexaanova.crm.dao;

import com.nexaanova.crm.model.UserAccount;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    private final JdbcTemplate jdbc;
    private final RowMapper<UserAccount> mapper = (rs, row) -> {
        UserAccount user = new UserAccount();
        user.setId(rs.getLong("id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setRole(rs.getString("role"));
        user.setActive(rs.getBoolean("active"));
        return user;
    };

    public UserDao(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    public List<UserAccount> findAll() {
        return jdbc.query("SELECT id,name,email,role,active FROM users ORDER BY name", mapper);
    }

    public UserAccount findById(long id) {
        List<UserAccount> users = jdbc.query("SELECT id,name,email,role,active FROM users WHERE id=?", mapper, id);
        return users.isEmpty() ? null : users.get(0);
    }

    public UserAccount findByEmail(String email) {
        List<UserAccount> users = jdbc.query("SELECT id,name,email,role,active FROM users WHERE email=?", mapper, email);
        return users.isEmpty() ? null : users.get(0);
    }

    public String findPasswordHash(long id) {
        return jdbc.queryForObject("SELECT password_hash FROM users WHERE id=?", String.class, id);
    }

    public long create(UserAccount user, String hash) {
        return DaoHelper.insert(jdbc, "INSERT INTO users(name,email,password_hash,role,active) VALUES(?,?,?,?,TRUE)",
                user.getName(), user.getEmail(), hash, user.getRole());
    }

    public void setActive(long id, boolean active) {
        jdbc.update("UPDATE users SET active=? WHERE id=?", active, id);
    }

    public int count() { return jdbc.queryForObject("SELECT COUNT(*) FROM users", Integer.class); }
}
