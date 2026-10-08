package com.nexaanova.crm.controller;

import com.nexaanova.crm.dao.UserDao;
import com.nexaanova.crm.model.LoginRequest;
import com.nexaanova.crm.model.UserAccount;
import com.nexaanova.crm.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService service;
    private final UserDao users;

    public AuthController(UserService service, UserDao users) { this.service = service; this.users = users; }

    @GetMapping("/session")
    public Map<String, Object> session(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Long id = (Long) session.getAttribute("userId");
        UserAccount user = id == null ? null : users.findById(id);
        if (user != null && !user.getActive()) {
            session.invalidate();
            session = request.getSession(true);
            user = null;
        }
        if (session.getAttribute("csrfToken") == null) {
            session.setAttribute("csrfToken", UUID.randomUUID().toString());
        }
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("user", user);
        data.put("csrfToken", session.getAttribute("csrfToken"));
        return data;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginRequest credentials, HttpServletRequest request) {
        UserAccount user = service.authenticate(credentials.getEmail(), credentials.getPassword());
        request.changeSessionId();
        request.getSession().setAttribute("userId", user.getId());
        request.getSession().setAttribute("csrfToken", UUID.randomUUID().toString());
        return session(request);
    }

    @PostMapping("/logout")
    public Map<String, String> logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return Map.of("message", "Signed out.");
    }
}
