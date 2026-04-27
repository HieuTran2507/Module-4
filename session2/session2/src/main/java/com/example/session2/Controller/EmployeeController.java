package com.example.session2.Controller;

import com.example.session2.Model.dto.EmployeeFilter;
import com.example.session2.Model.entity.Employee;
import com.example.session2.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    // [Bài tập 3 + 6] Lấy danh sách + ResponseEntity
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    // [Bài tập 4] Lấy chi tiết dùng @PathVariable
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeByID(@PathVariable int id){
        Employee employee = employeeService.getEmployeeByID(id);
        if (employee != null) return ResponseEntity.ok(employee);
        else return ResponseEntity.notFound().build();
    }

    // [Bài tập 4] Lấy chi tiết dùng @RequestParam
    // /api/employees/search?name=Van
    @GetMapping("/search")
    public ResponseEntity<List<Employee>> searchEmployee(@RequestParam String name){
        return ResponseEntity.ok(employeeService.getEmployeeByName(name));
    }

    // [Bài tập 4] Lọc nâng cao dùng @ModelAttribute
    // URL: /api/employees/filter?name=A&department=IT
    @GetMapping("/filter")
    public ResponseEntity<List<Employee>> filterEmployee(@ModelAttribute EmployeeFilter filter){
        return ResponseEntity.ok(employeeService.filterEmployees(filter));
    }

    // // [Bài tập 5 + 6] Thêm mới dùng @PostMapping, @RequestBody và Status 201
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee e){
        Employee newEmployee = employeeService.addEmployee(e);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEmployee);
    }

    // [Bài tập 5] Cập nhật dùng @PutMapping
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee e){
        Employee updateE = employeeService.updateEmployee(id,e);
        if (updateE != null) return ResponseEntity.ok(updateE);
        else return ResponseEntity.notFound().build();
    }

    // [Bài tập 5 + 6] Xóa dùng @DeleteMapping và Status 204/200
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id){
        Boolean chk = employeeService.deleteEmployee(id);
        if (chk) return ResponseEntity.ok("xóa thnahf công");
        else return ResponseEntity.status(HttpStatus.CREATED).body("khng tìm thấy nhân viên");
    }

}
