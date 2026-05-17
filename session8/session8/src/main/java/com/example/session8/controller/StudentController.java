package com.example.session8.controller;

import com.example.session8.exception.StudentNotFoundException;
import com.example.session8.model.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private List<Student> students = new ArrayList<>();

    // Giả lập dữ liệu trong Constructor
    public StudentController() {
        students.add(new Student("SV001", "Nguyen Van A"));
        students.add(new Student("SV002", "Tran Thi B"));
        students.add(new Student("SV003", "Le Van C"));
    }

    @GetMapping("/{id}")
    public Student getStudentByID(@PathVariable String id){
        for (Student s : students) {
            // nếu tìm thấy thì trả về
            if (s.getId().equalsIgnoreCase(id)) {
                return s;
            }
        }
        throw new StudentNotFoundException("không tìm thấy sinh viên");
    }
}
