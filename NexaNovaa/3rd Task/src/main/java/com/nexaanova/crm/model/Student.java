package com.nexaanova.crm.model;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity @Table(name = "students")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long studentId;
    @OneToOne(fetch=FetchType.LAZY) @JoinColumn(name="admission_id", unique=true, nullable=false) private Admission admission;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="user_id") private User user;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="trainer_id") private User trainer;
    @Column(length=100) private String batchName;
    private LocalDate startDate;
    private LocalDate endDate;
    @Column(precision=5, scale=2) private BigDecimal attendance = BigDecimal.ZERO;
    @Column(length=15) private String status = "Active";
    @CreationTimestamp private LocalDateTime createdAt;
}