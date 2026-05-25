package com.example.session10.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class EmployeeUpdateDTO {
    @NotBlank(message = "họ tên không được để trống")
    @Size(min = 5, message = "họ tên từ 5 ký tự trở lên")
    private String fullname;

    @Email(message = "email không đúng định dạng")
    private String email;

    private MultipartFile avatarFile;
}
