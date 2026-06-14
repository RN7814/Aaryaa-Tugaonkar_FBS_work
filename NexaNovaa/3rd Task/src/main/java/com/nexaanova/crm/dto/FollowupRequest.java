package com.nexaanova.crm.dto;
import lombok.Data;
import java.time.LocalDate;
@Data
public class FollowupRequest {
    private Long enquiryId;
    private LocalDate followupDate;
    private String remarks;
}