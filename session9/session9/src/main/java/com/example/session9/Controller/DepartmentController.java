package com.example.session9.Controller;

import com.example.session9.Service.DepartmentService;
import com.example.session9.model.DTO.DepartmentDTO;
import com.example.session9.model.DTO.Response;
import com.example.session9.model.Entity.Department;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    DepartmentService ds;

    @PostMapping
    public ResponseEntity<?> createDepartment(@Valid @RequestBody DepartmentDTO dto){
        Department d = ds.createDepartment(dto);

        Response<Department> response = Response.<Department>builder()
                .status("SUCCESS")
                .message("tạo phòng ban thành công")
                .data(d)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
