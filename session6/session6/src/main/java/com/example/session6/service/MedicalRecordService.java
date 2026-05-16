package com.example.session6.service;

import com.example.session6.exception.ResourceNotFoundException;
import com.example.session6.model.dto.MedicalRecordRequest;
import com.example.session6.model.entity.Doctor;
import com.example.session6.model.entity.MedicalRecord;
import com.example.session6.model.entity.Patient;
import com.example.session6.repository.DoctorRepository;
import com.example.session6.repository.MedicalRecordRepository;
import com.example.session6.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService {
    @Autowired
    private MedicalRecordRepository mr;
    @Autowired
    private DoctorRepository dr;
    @Autowired
    private PatientRepository pr;

    public MedicalRecord createMedicalRecord(MedicalRecordRequest request){
        // check doctor id
        Doctor d = dr.findById(request.getDoctorID())
                .orElseThrow(()->new ResourceNotFoundException("không tìm thấy bác sĩ"));
        // chech patient
        Patient p = pr.findById(request.getPatientID())
                .orElseThrow(()->new ResourceNotFoundException("không tìm thấy bệnh nhân"));
        // chech bệnh nhân & processing
        List<MedicalRecord> listRecord = mr.findByPatientIdAndStatus(
                request.getPatientID(), MedicalRecord.Status.processing);

        if (!listRecord.isEmpty()) throw new ResourceNotFoundException("bệnh nhân có hồ sơ chưa hoàn thành");

        // nếu done thì tạo record mới
        MedicalRecord newMedicalRecord = new MedicalRecord(
                request.getDiagnosis(),
                MedicalRecord.Status.processing,
                d,
                p
        );
        return mr.save(newMedicalRecord);
    }
}
