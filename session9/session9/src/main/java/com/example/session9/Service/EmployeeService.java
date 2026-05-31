package com.example.session9.Service;

import com.example.session9.Repository.DepartmentRepository;
import com.example.session9.Repository.EmployeeRepository;
import com.example.session9.model.DTO.EmployeeDTO;
import com.example.session9.model.Entity.Department;
import com.example.session9.model.Entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository er;
    @Autowired
    DepartmentRepository dr;

    public Employee addEmployee(EmployeeDTO dto){
        Department department = dr.findById(dto.getDepartmentId()).orElseThrow(()->
                new RuntimeException("không tìm thấy phòng ban"));

        Employee employee = Employee.builder()
                .fullname(dto.getFullname())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .salary(dto.getSalary())
                .department(department)
                .build();

        return er.save(employee);
    }
}
