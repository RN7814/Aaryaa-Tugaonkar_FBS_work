package com.nexaanova.crm.model;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity @Table(name = "users")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long userId;
    @Column(nullable=false, length=100) private String fullName;
    @Column(unique=true, nullable=false, length=150) private String email;
    @Column(nullable=false, length=255) private String passwordHash;
    @Column(length=15) private String phone;
    @Enumerated(EnumType.STRING) @Column(nullable=false) private Role role;
    @Column(nullable=false) private Boolean isActive = true;
    @CreationTimestamp private LocalDateTime createdAt;
}