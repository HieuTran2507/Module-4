package com.example.session10.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.session10.Repository.EmployeeRepository;
import com.example.session10.model.dto.EmployeeCreateDTO;
import com.example.session10.model.dto.EmployeeUpdateDTO;
import com.example.session10.model.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Map;

@Service
@AllArgsConstructor
@Builder
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final Cloudinary cloudinary;

    public Employee create(EmployeeCreateDTO employeeDTO)throws IOException {
        // upload image
        Map uploadResult = cloudinary.uploader().upload(
                employeeDTO.getAvatarFile().getBytes(),
                ObjectUtils.emptyMap()
        );

        // lấy url ảnh
        String imageUrl =
                uploadResult.get("url").toString();

        Employee employee = Employee.builder()
                .fullName(employeeDTO.getFullName())
                .email(employeeDTO.getEmail())
                .department(employeeDTO.getDepartment())
                .avatarUrl(imageUrl)
                .build();

        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee update(Long id, EmployeeUpdateDTO dto) throws IOException {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // update text fields
        employee.setFullName(dto.getFullname());
        employee.setEmail(dto.getEmail());

        // nếu có upload ảnh mới
        if (dto.getAvatarFile() != null && !dto.getAvatarFile().isEmpty()) {

            Map uploadResult = cloudinary.uploader().upload(
                    dto.getAvatarFile().getBytes(),
                    ObjectUtils.emptyMap()
            );

            String newAvatarUrl = uploadResult.get("url").toString();

            employee.setAvatarUrl(newAvatarUrl);
        }

        // nếu null → giữ nguyên avatarUrl cũ

        return employeeRepository.save(employee);
    }
}
