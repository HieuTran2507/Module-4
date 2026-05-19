package com.example.session10.controller;

import com.example.session10.model.entity.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class EmployeeController {
    @GetMapping("/api/v1/employees")
    public List<Employee> getEmployees(){
        return List.of(
                new Employee(1L, "Nguyen Van A", 1000),
                new Employee(2L, "Tran Thi B", 1200),
                new Employee(3L, "Le Van C", 1500)
        );
    }

    @GetMapping("/api/v1/auth/test")
    public List<Employee> getEmployeesWithAuth(){
        return List.of(
                new Employee(1L, "Nguyen Van A", 1000),
                new Employee(2L, "Tran Thi B", 1200),
                new Employee(3L, "Le Van C", 1500)
        );
    }
}
