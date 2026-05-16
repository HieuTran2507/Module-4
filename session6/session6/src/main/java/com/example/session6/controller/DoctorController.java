package com.example.session6.controller;

import com.example.session6.model.entity.Doctor;
import com.example.session6.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    private DoctorService ds;

    @PostMapping
    public ResponseEntity<?> addDoctor(@RequestBody Doctor d){
        return ResponseEntity.ok(ds.addDoctor(d));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Long id){
        Boolean chk = ds.deleteDoctor(id);
        if (chk) return ResponseEntity.ok("xóa thành công");
        else return ResponseEntity.badRequest().body("không tìm thấy bác sĩ với id: "+id);
    }

    @GetMapping
    public ResponseEntity<?> getAllDoctors(){
        List<Doctor> d = ds.getAllDoctors();
        if (d.isEmpty()) return ResponseEntity.badRequest().body("danh sách rỗng");
        else return ResponseEntity.ok(d);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDoctor(@PathVariable Long id, @RequestBody Doctor d){
        Boolean chk = ds.updateDoctor(id, d);
        if (chk) return ResponseEntity.ok("update thành công");
        else return ResponseEntity.badRequest().body("không tìm thấy bác sĩ với id: "+id);
    }

}
