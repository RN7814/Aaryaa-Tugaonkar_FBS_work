package com.nexaanova.crm.model;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity @Table(name = "enquiries")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Enquiry {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long enquiryId;
    @Column(nullable=false, length=100) private String studentName;
    @Column(nullable=false, length=15) private String phone;
    @Column(length=150) private String email;
    @Column(length=100) private String city;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="course_id") private Course course;
    @Column(nullable=false, length=50) private String source;
    private LocalDate enquiryDate = LocalDate.now();
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="assigned_to") private User assignedTo;
    @Enumerated(EnumType.STRING) private EnquiryStatus status = EnquiryStatus.New;
    @Column(length=150) private String collegeName;
    @Column(length=100) private String qualification;
    @Column(precision=5, scale=2) private BigDecimal marksPercentage;
    @Column(columnDefinition="TEXT") private String remarks;
    @CreationTimestamp private LocalDateTime createdAt;
}