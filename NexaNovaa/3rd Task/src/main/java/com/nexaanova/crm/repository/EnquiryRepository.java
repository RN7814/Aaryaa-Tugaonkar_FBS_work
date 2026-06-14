package com.nexaanova.crm.repository;
import com.nexaanova.crm.model.Enquiry;
import com.nexaanova.crm.model.EnquiryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnquiryRepository extends JpaRepository<Enquiry, Long> {
    java.util.List<Enquiry> findByAssignedTo_UserId(Long userId);
    java.util.List<Enquiry> findByStatus(EnquiryStatus status);
    long countByStatus(EnquiryStatus status);
}