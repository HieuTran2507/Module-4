package com.example.session6.repository;

import com.example.session6.model.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord,Long> {
    List<MedicalRecord> findByPatientIdAndStatus(Long patientID, MedicalRecord.Status status);
}
