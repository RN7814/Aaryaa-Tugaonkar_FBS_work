package com.nexaanova.crm.dto;
import lombok.Data;
import java.math.BigDecimal;
@Data
public class AdmissionRequest {
    private Long enquiryId;
    private Long courseId;
    private BigDecimal totalFees;
    private BigDecimal feesPaid;
    private String paymentType;
    private Boolean installment;
}