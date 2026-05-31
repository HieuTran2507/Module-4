package com.example.session9.model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DepartmentDTO {
    @NotBlank(message = "Tên phòng không được để trống")
    @Size(min = 5, max = 50, message = "tên phòng phải từ 5 - 50 ký tự")
    private String name;

    @Size(max = 100, message = "mô tả không quá 100 ký tự")
    private String description;

}
