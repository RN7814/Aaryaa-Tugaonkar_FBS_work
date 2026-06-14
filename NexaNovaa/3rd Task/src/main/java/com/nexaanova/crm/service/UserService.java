package com.nexaanova.crm.service;
import com.nexaanova.crm.model.User;
import com.nexaanova.crm.model.Role;
import com.nexaanova.crm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service @RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(User user) {
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        return userRepository.save(user);
    }

    public List<User> getAllUsers() { return userRepository.findAll(); }

    public List<User> getCounselors() {
        return userRepository.findAll().stream()
            .filter(u -> u.getRole() == Role.counselor && u.getIsActive())
            .toList();
    }

    public User toggleActive(Long id) {
        User u = userRepository.findById(id).orElseThrow();
        u.setIsActive(!u.getIsActive());
        return userRepository.save(u);
    }
}