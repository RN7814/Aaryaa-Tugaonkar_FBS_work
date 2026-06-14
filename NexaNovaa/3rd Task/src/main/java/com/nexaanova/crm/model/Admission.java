package com.nexaanova.crm.model;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity @Table(name = "admissions")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Admission {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long admissionId;
    @OneToOne(fetch=FetchType.LAZY) @JoinColumn(name="enquiry_id", unique=true, nullable=false) private Enquiry enquiry;
    @Column(nullable=false, length=100) private String studentName;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="course_id", nullable=false) private Course course;
    @Column(nullable=false, precision=10, scale=2) private BigDecimal totalFees;
    @Column(nullable=false, precision=10, scale=2) private BigDecimal feesPaid = BigDecimal.ZERO;
    @Column(nullable=false, length=15) private String paymentStatus = "Pending";
    @Column(nullable=false, length=10) private String paymentType;
    private Boolean installment = false;
    private LocalDate admissionDate = LocalDate.now();
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="admitted_by") private User admittedBy;
    @CreationTimestamp private LocalDateTime createdAt;
}