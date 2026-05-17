package com.example.ss8_ex5.Controller;

import com.example.ss8_ex5.model.entity.Product;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    // giả lập database
    List<Product> products = new ArrayList<>();

    // upload folder
    private static final String UPLOAD_DIR =
            "D:\\TSU_251028\\module4\\session8\\ss8_ex5\\upload";

    @PostMapping
    public Product createProduct(

            @RequestParam String name,

            @RequestParam Double price,

            @RequestParam("image") MultipartFile image

    ) throws IOException {

        // validate name
        if (name == null || name.trim().isEmpty()) {
            throw new RuntimeException(
                    "Tên sản phẩm là bắt buộc"
            );
        }

        // validate image
        if (image == null || image.isEmpty()) {
            throw new RuntimeException(
                    "Ảnh sản phẩm là bắt buộc"
            );
        }

        // lấy tên file gốc
        String originalFileName =
                image.getOriginalFilename();

        // lấy extension
        String extension =
                originalFileName.substring(
                        originalFileName.lastIndexOf(".")
                );

        // tạo tên file mới bằng UUID
        String newFileName =
                UUID.randomUUID() + extension;

        // tạo folder nếu chưa có
        File directory = new File(UPLOAD_DIR);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        // file đích
        File dest = new File(directory, newFileName);

        // lưu file
        image.transferTo(dest);

        // tạo product
        Product product = new Product(
                (long) (products.size() + 1),
                name,
                price,
                newFileName
        );

        // lưu list
        products.add(product);

        return product;
    }
}
