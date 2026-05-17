package com.example.ss8_ex3.contoller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/files")
public class FileController {
    @PostMapping("/upload")
    public String uploadFile(
            @RequestParam("image") MultipartFile file
    ) throws IOException {

        // kiểm tra file rỗng
        if (file.isEmpty()) {
            return "File rỗng";
        }

        // lấy tên file
        String fileName = file.getOriginalFilename();

        // kiểm tra extension
        if (fileName == null ||
                !(fileName.endsWith(".png")
                        || fileName.endsWith(".jpg"))) {

            throw new RuntimeException(
                    "Chỉ chấp nhận file .png hoặc .jpg"
            );
        }

        // đường dẫn upload
        String uploadDir =
                "D:\\TSU_251028\\module4\\session8\\ss8_ex3\\upload";

        // tạo folder nếu chưa có
        File directory = new File(uploadDir);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        // file đích
        File dest = new File(directory, fileName);

        // lưu file
        file.transferTo(dest);

        return "Upload thành công: " + fileName;
    }
}
