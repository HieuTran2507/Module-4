package com.example.session10.controller;

import com.example.session10.model.dto.EmployeeCreateDTO;
import com.example.session10.model.dto.EmployeeUpdateDTO;
import com.example.session10.model.entity.Employee;
import com.example.session10.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService es;

    @PostMapping
    public ResponseEntity<?> create(@ModelAttribute EmployeeCreateDTO dto)throws IOException {
        return ResponseEntity.ok(
                es.create(dto)
        );
    }
    @GetMapping
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("ok");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(
            @PathVariable Long id,
            @ModelAttribute @Valid EmployeeUpdateDTO dto
    ) throws IOException {

        return ResponseEntity.ok(
                es.update(id, dto)
        );
    }
}
