package com.example.session9.Service;

import com.example.session9.Exception.DuplicateResourceException;
import com.example.session9.Exception.InvalidFileException;
import com.example.session9.Exception.ResourceNotFoundException;
import com.example.session9.Repository.DepartmentRepository;
import com.example.session9.Repository.EmployeeRepository;
import com.example.session9.model.DTO.EmployeeDTO;
import com.example.session9.model.Entity.Department;
import com.example.session9.model.Entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository er;
    @Autowired
    DepartmentRepository dr;

    public Employee addEmployee(EmployeeDTO dto){

        if(er.existsByEmail(dto.getEmail())){
            throw new DuplicateResourceException(
                    "Email đã được sử dụng"
            );
        }

        Department department = dr.findById(dto.getDepartmentId()).orElseThrow(()->
                new ResourceNotFoundException("không tìm thấy phòng ban"));

        Employee employee = Employee.builder()
                .fullname(dto.getFullname())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .salary(dto.getSalary())
                .department(department)
                .build();

        return er.save(employee);
    }

    public Employee uploadAvatar(
            Long employeeId,
            MultipartFile file
    ) throws IOException {

        Employee employee =
                er.findById(employeeId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Nhân viên không tồn tại"
                                ));

        if(file.isEmpty()){
            throw new InvalidFileException(
                    "File không được để trống"
            );
        }

        // validate size < 2MB
        if(file.getSize() > 2 * 1024 * 1024){
            throw new InvalidFileException(
                    "Kích thước file vượt quá 2MB"
            );
        }

        String fileName =
                file.getOriginalFilename();

        String extension =
                fileName.substring(
                        fileName.lastIndexOf(".") + 1
                ).toLowerCase();

        if(!extension.equals("jpg")
                && !extension.equals("jpeg")
                && !extension.equals("png")){

            throw new InvalidFileException(
                    "Định dạng file không hợp lệ"
            );
        }

        Path uploadPath =
                Paths.get("uploads");

        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }

        String newFileName =
                UUID.randomUUID()
                        + "_" + fileName;

        Path destination =
                uploadPath.resolve(newFileName);

        Files.copy(
                file.getInputStream(),
                destination,
                StandardCopyOption.REPLACE_EXISTING
        );

        employee.setAvatarUrl(
                "uploads/" + newFileName
        );

        return er.save(employee);
    }
}
