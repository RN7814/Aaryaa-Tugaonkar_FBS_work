package com.nexaanova.crm.repository;
import com.nexaanova.crm.model.Followup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowupRepository extends JpaRepository<Followup, Long> {
    java.util.List<Followup> findByFollowupDateAndFollowupStatus(java.time.LocalDate date, String status);
    java.util.List<Followup> findByCounselor_UserId(Long counselorId);
}