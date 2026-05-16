package com.example.session6.controller;

import com.example.session6.model.dto.PaginationResponse;
import com.example.session6.model.entity.Patient;
import com.example.session6.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService ps;

    @PostMapping
    public ResponseEntity<?> addPatient(@RequestBody Patient p){
        return ResponseEntity.ok(ps.addPatient(p));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable Long id){
        Boolean chk = ps.deletePatient(id);
        if (chk) return ResponseEntity.ok("xóa thành công");
        else return ResponseEntity.badRequest().body("không tìm thấy bệnh nhân với id: "+id);
    }

    @GetMapping("/search")
    public ResponseEntity<PaginationResponse> paginationAndSearch(
            @RequestParam(defaultValue = "") String patientName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int pagesize
    ){
        return ResponseEntity.ok(ps.searchPatient(patientName,page,pagesize));
    }
}
