package com.nexaanova.crm.service;
import com.nexaanova.crm.dto.CallRecordRequest;
import com.nexaanova.crm.model.CallRecord;
import com.nexaanova.crm.model.EnquiryStatus;
import com.nexaanova.crm.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service @RequiredArgsConstructor
public class CallRecordService {

    private final CallRecordRepository callRecordRepository;
    private final EnquiryRepository enquiryRepository;
    private final UserRepository userRepository;

    public CallRecord addCall(CallRecordRequest req, Long counselorId) {
        CallRecord cr = new CallRecord();
        enquiryRepository.findById(req.getEnquiryId()).ifPresent(e -> {
            cr.setEnquiry(e);
            // Auto-update enquiry status based on call outcome
            switch (req.getCallStatus()) {
                case "Interested"     -> e.setStatus(EnquiryStatus.Follow_up);
                case "Not Interested" -> e.setStatus(EnquiryStatus.Closed);
                case "CNR"            -> e.setStatus(EnquiryStatus.CNR);
                default               -> e.setStatus(EnquiryStatus.Called);
            }
            enquiryRepository.save(e);
        });
        userRepository.findById(counselorId).ifPresent(cr::setCounselor);
        cr.setCallDate(req.getCallDate());
        cr.setCallStatus(req.getCallStatus());
        cr.setRemarks(req.getRemarks());
        return callRecordRepository.save(cr);
    }

    public List<CallRecord> getByEnquiry(Long enquiryId) {
        return callRecordRepository.findByEnquiry_EnquiryId(enquiryId);
    }

    public List<CallRecord> getByCounselor(Long counselorId) {
        return callRecordRepository.findByCounselor_UserId(counselorId);
    }
}