package com.example.session7.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public class StudentDTO {
    @NotBlank(message = "họ tên không được để trống")
    @Size(min = 5, max = 100, message = "họ tên từ từ 5 - 100 ký tự")
    private String fullname;

    @NotBlank(message = "chuyên ngành không được để trống")
    private String major;

    @Min(value = 18, message = "vui lòng nhập tuổi từ 18 trở lên")
    private int age;

    @Min(value = 0, message = "vui lòng nhập điểm >= 0")
    @Max(value = 10, message = "vui lòng nhập điểm <= 10")
    private double gpa;

    @Pattern(regexp = "^SV\\d{4}$", message = "mã sinh viên có dạng SV0001")
    private String studentCode;

    @Valid
    private ParentsDTO parents;

    public StudentDTO() {
    }

    public StudentDTO(String fullname, String major, int age, double gpa, String studentCode, ParentsDTO parents) {
        this.fullname = fullname;
        this.major = major;
        this.age = age;
        this.gpa = gpa;
        this.studentCode = studentCode;
        this.parents = parents;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public ParentsDTO getParents() {
        return parents;
    }

    public void setParents(ParentsDTO parents) {
        this.parents = parents;
    }
}
