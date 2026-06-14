package com.nexaanova.crm.dto;
import lombok.Data;
import java.math.BigDecimal;
@Data
public class EnquiryRequest {
    private String studentName;
    private String phone;
    private String email;
    private String city;
    private Long courseId;
    private String source;
    private Long assignedToId;
    private String collegeName;
    private String qualification;
    private BigDecimal marksPercentage;
    private String remarks;
}