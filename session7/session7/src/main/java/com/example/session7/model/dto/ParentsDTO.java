package com.example.session7.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ParentsDTO {
    @NotBlank(message = "không để trống tên")
    @Pattern(regexp ="^[A-Z].*", message = "Viết hoa chữ cái đầu")
    private String parentName;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^0\\d{9}$", message = "SĐT phải bắt đầu bằng 0 và có 10 số")
    private String phoneNumber;

    public ParentsDTO() {
    }

    public ParentsDTO(String parentName, String phoneNumber) {
        this.parentName = parentName;
        this.phoneNumber = phoneNumber;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
