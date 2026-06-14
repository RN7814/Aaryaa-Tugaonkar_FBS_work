package com.nexaanova.crm.service;
import com.nexaanova.crm.dto.FollowupRequest;
import com.nexaanova.crm.model.Followup;
import com.nexaanova.crm.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service @RequiredArgsConstructor
public class FollowupService {

    private final FollowupRepository followupRepository;
    private final EnquiryRepository enquiryRepository;
    private final UserRepository userRepository;

    public Followup schedule(FollowupRequest req, Long counselorId) {
        Followup f = new Followup();
        enquiryRepository.findById(req.getEnquiryId()).ifPresent(f::setEnquiry);
        userRepository.findById(counselorId).ifPresent(f::setCounselor);
        f.setFollowupDate(req.getFollowupDate());
        f.setRemarks(req.getRemarks());
        return followupRepository.save(f);
    }

    public List<Followup> getTodayFollowups() {
        return followupRepository.findByFollowupDateAndFollowupStatus(LocalDate.now(), "Pending");
    }

    public List<Followup> getByCounselor(Long counselorId) {
        return followupRepository.findByCounselor_UserId(counselorId);
    }

    public Followup updateStatus(Long id, String status, String remarks) {
        Followup f = followupRepository.findById(id).orElseThrow();
        f.setFollowupStatus(status);
        if (remarks != null) f.setRemarks(remarks);
        return followupRepository.save(f);
    }
}