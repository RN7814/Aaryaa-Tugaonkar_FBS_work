package com.nexaanova.crm.repository;
import com.nexaanova.crm.model.Admission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmissionRepository extends JpaRepository<Admission, Long> {
    java.util.Optional<Admission> findByEnquiry_EnquiryId(Long enquiryId);
}