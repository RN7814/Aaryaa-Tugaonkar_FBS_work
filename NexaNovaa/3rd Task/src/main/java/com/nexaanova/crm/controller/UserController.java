package com.nexaanova.crm.controller;
import com.nexaanova.crm.model.User;
import com.nexaanova.crm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/counselors")
    public ResponseEntity<List<User>> counselors() {
        return ResponseEntity.ok(userService.getCounselors());
    }

    @PutMapping("/{id}/toggle")
    public ResponseEntity<User> toggle(@PathVariable Long id) {
        return ResponseEntity.ok(userService.toggleActive(id));
    }
}