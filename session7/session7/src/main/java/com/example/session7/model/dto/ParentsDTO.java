package com.example.session7.model.dto;

import com.example.session7.validation.ViettelPhone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ParentsDTO {
    @NotBlank(message = "không để trống tên")
    @Pattern(regexp ="^[A-Z].*", message = "Viết hoa chữ cái đầu")
    private String parentName;

    @NotBlank(message = "Số điện thoại không được để trống")
    @ViettelPhone
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
