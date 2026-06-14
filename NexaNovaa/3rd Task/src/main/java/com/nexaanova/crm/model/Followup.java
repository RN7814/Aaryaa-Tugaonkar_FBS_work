package com.nexaanova.crm.model;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity @Table(name = "followups")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Followup {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long followupId;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="enquiry_id", nullable=false) private Enquiry enquiry;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="counselor_id", nullable=false) private User counselor;
    @Column(nullable=false) private LocalDate followupDate;
    @Column(length=20) private String followupStatus = "Pending";
    @Column(columnDefinition="TEXT") private String remarks;
    @CreationTimestamp private LocalDateTime createdAt;
}