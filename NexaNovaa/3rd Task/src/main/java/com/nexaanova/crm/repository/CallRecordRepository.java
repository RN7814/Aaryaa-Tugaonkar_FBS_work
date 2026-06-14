package com.nexaanova.crm.repository;
import com.nexaanova.crm.model.CallRecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallRecordRepository extends JpaRepository<CallRecord, Long> {
    java.util.List<CallRecord> findByEnquiry_EnquiryId(Long enquiryId);
    java.util.List<CallRecord> findByCounselor_UserId(Long counselorId);
}