package com.nexaanova.crm.dto;
import lombok.Data;
import java.time.LocalDateTime;
@Data
public class CallRecordRequest {
    private Long enquiryId;
    private LocalDateTime callDate;
    private String callStatus;
    private String remarks;
}