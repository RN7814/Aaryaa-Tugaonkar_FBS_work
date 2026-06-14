package com.nexaanova.crm.repository;
import com.nexaanova.crm.model.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    java.util.List<Student> findByTrainer_UserId(Long trainerId);
    java.util.Optional<Student> findByAdmission_AdmissionId(Long admissionId);
}