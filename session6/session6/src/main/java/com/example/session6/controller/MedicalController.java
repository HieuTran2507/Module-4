package com.example.session6.controller;

import com.example.session6.model.dto.MedicalRecordRequest;
import com.example.session6.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medical_record")
public class MedicalController {
    @Autowired
    private MedicalRecordService ms;

    @PostMapping
    public ResponseEntity<?> createMedicalRecord(@RequestBody MedicalRecordRequest request){
        System.out.println("here");
        return ResponseEntity.ok(ms.createMedicalRecord(request));
    }
}
