package com.example.session9.Service;

import com.example.session9.Repository.DepartmentRepository;
import com.example.session9.model.DTO.DepartmentDTO;
import com.example.session9.model.Entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository dp;

    public Department createDepartment(DepartmentDTO dto){
        Department d = Department.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
        return dp.save(d);
    }
}
