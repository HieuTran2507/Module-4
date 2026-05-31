package com.example.session12.Model.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank
    private String phone;

    @Email
    private String email;

    @NotBlank
    private String password;
}
