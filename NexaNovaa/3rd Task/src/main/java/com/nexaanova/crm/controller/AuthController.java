package com.nexaanova.crm.controller;
import com.nexaanova.crm.dto.LoginRequest;
import com.nexaanova.crm.dto.LoginResponse;
import com.nexaanova.crm.model.User;
import com.nexaanova.crm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // POST /api/auth/login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        User user = userRepository.findAll().stream()
            .filter(u -> u.getEmail().equals(req.getEmail()) && u.getIsActive())
            .findFirst()
            .orElse(null);

        if (user == null || !passwordEncoder.matches(req.getPassword(), user.getPasswordHash()))
            return ResponseEntity.status(401).body("Invalid credentials");

        // Simple token placeholder — replace with JwtUtil in production
        String token = "token_" + user.getUserId() + "_" + user.getRole();
        return ResponseEntity.ok(new LoginResponse(token, user.getRole().name(), user.getFullName(), user.getUserId()));
    }
}