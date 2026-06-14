package com.nexaanova.crm.model;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity @Table(name = "courses")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Course {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long courseId;
    @Column(nullable=false, unique=true, length=150) private String courseName;
    @Column(columnDefinition="TEXT") private String description;
    @Column(nullable=false, length=50) private String duration;
    @Column(nullable=false, precision=10, scale=2) private BigDecimal fees;
    @Column(nullable=false) private Boolean isActive = true;
}