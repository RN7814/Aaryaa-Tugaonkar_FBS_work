package com.nexaanova.crm.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexaanova.crm.dao.UserDao;
import com.nexaanova.crm.model.UserAccount;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Order(1)
public class SessionFilter extends OncePerRequestFilter {
    private final UserDao users;
    private final ObjectMapper json;

    public SessionFilter(UserDao users, ObjectMapper json) {
        this.users = users;
        this.json = json;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        response.setHeader("X-Content-Type-Options", "nosniff");
        response.setHeader("X-Frame-Options", "DENY");
        response.setHeader("Referrer-Policy", "same-origin");
        response.setHeader("Content-Security-Policy", "default-src 'self'; script-src 'self'; style-src 'self'; img-src 'self' data:; connect-src 'self'; frame-ancestors 'none'; form-action 'self'; base-uri 'self'");
        String path = request.getRequestURI();
        if (!path.startsWith("/api/")) {
            chain.doFilter(request, response);
            return;
        }
        response.setHeader("Cache-Control", "no-store");
        HttpSession session = request.getSession(false);
        boolean write = !"GET".equals(request.getMethod()) && !"HEAD".equals(request.getMethod());
        if (write && !validToken(request, session)) {
            fail(response, 403, "Your form session expired. Refresh the page and try again.");
            return;
        }
        if (path.equals("/api/auth/session") || path.equals("/api/auth/login")) {
            chain.doFilter(request, response);
            return;
        }
        Long id = session == null ? null : (Long) session.getAttribute("userId");
        UserAccount user = id == null ? null : users.findById(id);
        if (user == null || !user.getActive()) {
            if (session != null) { session.invalidate(); }
            fail(response, 401, "Please sign in to continue.");
            return;
        }
        request.setAttribute("user", user);
        if (write && "MANAGER".equals(user.getRole()) && !path.equals("/api/auth/logout")) {
            fail(response, 403, "The Manager account has read-only access.");
            return;
        }
        chain.doFilter(request, response);
    }

    private boolean validToken(HttpServletRequest request, HttpSession session) {
        String sent = request.getHeader("X-CSRF-Token");
        String expected = session == null ? null : (String) session.getAttribute("csrfToken");
        return sent != null && expected != null && MessageDigest.isEqual(
                sent.getBytes(StandardCharsets.UTF_8), expected.getBytes(StandardCharsets.UTF_8));
    }

    private void fail(HttpServletResponse response, int status, String message) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        json.writeValue(response.getWriter(), Map.of("message", message));
    }
}
