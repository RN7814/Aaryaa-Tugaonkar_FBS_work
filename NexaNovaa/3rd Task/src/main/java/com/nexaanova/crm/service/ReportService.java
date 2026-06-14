package com.nexaanova.crm.service;
import com.nexaanova.crm.dto.ReportSummary;
import com.nexaanova.crm.model.EnquiryStatus;
import com.nexaanova.crm.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service @RequiredArgsConstructor
public class ReportService {

    private final EnquiryRepository enquiryRepository;
    private final AdmissionRepository admissionRepository;
    private final FollowupRepository followupRepository;

    public ReportSummary getSummary() {
        return new ReportSummary(
            enquiryRepository.count(),
            enquiryRepository.countByStatus(EnquiryStatus.New),
            enquiryRepository.countByStatus(EnquiryStatus.CNR),
            enquiryRepository.countByStatus(EnquiryStatus.Converted),
            admissionRepository.count(),
            followupRepository.findByFollowupDateAndFollowupStatus(LocalDate.now(), "Pending").size()
        );
    }
}