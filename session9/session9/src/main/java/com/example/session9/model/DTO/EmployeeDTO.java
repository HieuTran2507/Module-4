package com.example.session9.model.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class EmployeeDTO {
    @NotBlank(message = "không để tên trống")
    private String fullname;

    @Email(message = "Email không đúng định dạng")
    @NotBlank(message = "Email không được để trống")
    private String email;

    @Pattern(
            regexp = "^(03|05|07|08|09)[0-9]{8}$",
            message = "Số điện thoại không đúng định dạng Việt Nam"
    )
    private String phone;

    @Min(
            value = 5000000,
            message = "Lương phải lớn hơn hoặc bằng 5.000.000"
    )
    private Double salary;

    @NotNull(message = "Phòng ban không được để trống")
    private Long departmentId;
}
