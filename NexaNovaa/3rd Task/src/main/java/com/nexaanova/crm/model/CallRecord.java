package com.nexaanova.crm.model;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity @Table(name = "call_records")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CallRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long callId;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="enquiry_id", nullable=false) private Enquiry enquiry;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="counselor_id", nullable=false) private User counselor;
    @Column(nullable=false) private LocalDateTime callDate;
    @Column(nullable=false, length=30) private String callStatus;
    @Column(columnDefinition="TEXT") private String remarks;
    @CreationTimestamp private LocalDateTime createdAt;
}