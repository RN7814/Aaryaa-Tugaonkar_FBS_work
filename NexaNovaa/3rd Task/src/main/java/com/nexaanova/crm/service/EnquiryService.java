package com.nexaanova.crm.service;
import com.nexaanova.crm.dto.EnquiryRequest;
import com.nexaanova.crm.model.*;
import com.nexaanova.crm.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service @RequiredArgsConstructor
public class EnquiryService {

    private final EnquiryRepository enquiryRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public Enquiry createEnquiry(EnquiryRequest req) {
        Enquiry e = new Enquiry();
        e.setStudentName(req.getStudentName());
        e.setPhone(req.getPhone());
        e.setEmail(req.getEmail());
        e.setCity(req.getCity());
        e.setSource(req.getSource());
        e.setCollegeName(req.getCollegeName());
        e.setQualification(req.getQualification());
        e.setMarksPercentage(req.getMarksPercentage());
        e.setRemarks(req.getRemarks());
        if (req.getCourseId() != null)
            courseRepository.findById(req.getCourseId()).ifPresent(e::setCourse);
        if (req.getAssignedToId() != null)
            userRepository.findById(req.getAssignedToId()).ifPresent(e::setAssignedTo);
        return enquiryRepository.save(e);
    }

    public List<Enquiry> getAll() { return enquiryRepository.findAll(); }

    public List<Enquiry> getByCounselor(Long counselorId) {
        return enquiryRepository.findByAssignedTo_UserId(counselorId);
    }

    public Enquiry assignCounselor(Long enquiryId, Long counselorId) {
        Enquiry e = enquiryRepository.findById(enquiryId).orElseThrow();
        userRepository.findById(counselorId).ifPresent(e::setAssignedTo);
        return enquiryRepository.save(e);
    }

    public Enquiry updateStatus(Long id, EnquiryStatus status) {
        Enquiry e = enquiryRepository.findById(id).orElseThrow();
        e.setStatus(status);
        return enquiryRepository.save(e);
    }

    public void delete(Long id) { enquiryRepository.deleteById(id); }
}