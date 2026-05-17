package com.example.md4_ex6.controller;

import com.example.md4_ex6.model.dto.CandidateDTO;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/candidates")
public class CandidateController {

    private static final String UPLOAD_DIR =
            "D:\\TSU_251028\\module4\\session8\\md4_ex6\\upload";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/apply")
    public String apply(

            @RequestPart("candidate") String candidateJson,

            @RequestPart("cv") MultipartFile cv

    ) throws IOException {

        // 1. Convert JSON -> DTO
        CandidateDTO candidate =
                objectMapper.readValue(candidateJson, CandidateDTO.class);

        // 2. Validate file
        if (cv.isEmpty()) {
            throw new RuntimeException("CV không được để trống");
        }

        String fileName = cv.getOriginalFilename();

        if (fileName == null || !fileName.endsWith(".pdf")) {
            throw new RuntimeException("Chỉ chấp nhận file PDF");
        }

        // 3. tạo folder
        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 4. đổi tên file an toàn
        String newFileName = UUID.randomUUID() + ".pdf";

        File dest = new File(dir, newFileName);

        // 5. lưu file
        cv.transferTo(dest);

        // 6. response
        return "Ứng tuyển thành công: "
                + candidate.getFullName()
                + " | CV: " + newFileName;
    }
}
