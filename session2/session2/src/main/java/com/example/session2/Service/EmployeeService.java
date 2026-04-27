package com.example.session2.Service;

import com.example.session2.Model.dto.EmployeeFilter;
import com.example.session2.Model.entity.Employee;
import com.example.session2.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee getEmployeeByID(int id){
        return employeeRepository.findByID(id).orElse(null);
    }

    public List<Employee> getEmployeeByName(String name){
        return employeeRepository.findAll().stream()
                .filter(e->e.getFullName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Employee> filterEmployees(EmployeeFilter f){
        return employeeRepository.filterEmployees(f);
    }

    public Employee addEmployee(Employee e){
        employeeRepository.addEmployee(e);
        return e;
    }

    public Employee updateEmployee(int id, Employee e){
        if (employeeRepository.findByID(id).isPresent()){
            employeeRepository.updateEmployee(id, e);
            return e;
        }
        return null;
    }

    public Boolean deleteEmployee(int id){
        return employeeRepository.deleteEmployee(id);
    }
}
