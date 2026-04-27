package com.example.session2.Repository;

import com.example.session2.Model.dto.EmployeeFilter;
import com.example.session2.Model.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepository {
    private final List<Employee> employees = new ArrayList<>();

    public EmployeeRepository() {
        employees.add(new Employee(1, "Nguyen Van A", "a@gmail.com", "IT"));
        employees.add(new Employee(2, "Tran Thi B", "b@gmail.com", "HR"));
    }

    public List<Employee> findAll(){
        return this.employees;
    }

    public Optional<Employee> findByID(int id){
        return employees.stream().filter(e->e.getId()==id).findFirst();
    }

    public void addEmployee(Employee e){
        employees.add(e);
    }

    public void updateEmployee(int id, Employee newInfo){
        findByID(id).ifPresent(e->{
            e.setFullName(newInfo.getFullName());
            e.setEmail(newInfo.getEmail());
            e.setDepartment(newInfo.getDepartment());
        });
    }

    public Boolean deleteEmployee(int id){
        return employees.removeIf(e->e.getId()==id);
    }

    public List<Employee> filterEmployees(EmployeeFilter filter) {
        return employees.stream()
                // lọc theo name (fullName)
                .filter(e -> filter.getName() == null
                        || e.getFullName().toLowerCase().contains(filter.getName().toLowerCase()))

                // lọc theo department
                .filter(e -> filter.getDepartment() == null
                        || e.getDepartment().equalsIgnoreCase(filter.getDepartment()))

                .toList();
    }

}
