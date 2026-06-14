package com.nexaanova.crm.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data @AllArgsConstructor
public class ReportSummary {
    private long totalEnquiries;
    private long openLeads;
    private long cnrLeads;
    private long converted;
    private long totalAdmissions;
    private long followupsDueToday;
}