package com.example.session9.Controller;

import com.example.session9.Service.EmployeeService;
import com.example.session9.model.DTO.EmployeeDTO;
import com.example.session9.model.DTO.Response;
import com.example.session9.model.Entity.Employee;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService es;

    @PostMapping
    public ResponseEntity<?> addEmployee(@Valid @RequestBody EmployeeDTO dto){
        Employee employee = es.addEmployee(dto);

        Response<Employee> response = Response.<Employee>builder()
                .status("SUCCESS")
                .message("thêm nhân viên thành công")
                .data(employee)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/avatar/{id}")
    public ResponseEntity<Response<Employee>>
    uploadAvatar(
            @PathVariable Long id,
            @RequestParam("file")
            MultipartFile file
    ) throws IOException {

        Employee employee =
                es.uploadAvatar(id, file);

        Response<Employee> response =
                Response.<Employee>builder()
                        .status("SUCCESS")
                        .message("Upload ảnh thành công")
                        .data(employee)
                        .build();

        return ResponseEntity.ok(response);
    }
}
