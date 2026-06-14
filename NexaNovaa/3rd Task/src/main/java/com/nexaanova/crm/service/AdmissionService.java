package com.nexaanova.crm.service;
import com.nexaanova.crm.dto.AdmissionRequest;
import com.nexaanova.crm.model.*;
import com.nexaanova.crm.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service @RequiredArgsConstructor
public class AdmissionService {

    private final AdmissionRepository admissionRepository;
    private final EnquiryRepository enquiryRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public Admission convert(AdmissionRequest req, Long counselorId) {
        Enquiry enquiry = enquiryRepository.findById(req.getEnquiryId()).orElseThrow();
        Course course   = courseRepository.findById(req.getCourseId()).orElseThrow();
        User counselor  = userRepository.findById(counselorId).orElseThrow();

        Admission a = new Admission();
        a.setEnquiry(enquiry);
        a.setStudentName(enquiry.getStudentName());
        a.setCourse(course);
        a.setTotalFees(req.getTotalFees());
        a.setFeesPaid(req.getFeesPaid() != null ? req.getFeesPaid() : BigDecimal.ZERO);
        a.setPaymentType(req.getPaymentType());
        a.setInstallment(Boolean.TRUE.equals(req.getInstallment()));
        a.setAdmittedBy(counselor);

        BigDecimal balance = req.getTotalFees().subtract(a.getFeesPaid());
        a.setPaymentStatus(balance.compareTo(BigDecimal.ZERO) <= 0 ? "Paid"
                         : a.getFeesPaid().compareTo(BigDecimal.ZERO) == 0 ? "Pending" : "Partial");

        enquiry.setStatus(EnquiryStatus.Converted);
        enquiryRepository.save(enquiry);
        return admissionRepository.save(a);
    }

    public List<Admission> getAll() { return admissionRepository.findAll(); }
}