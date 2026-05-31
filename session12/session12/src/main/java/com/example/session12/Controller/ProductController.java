package com.example.session12.Controller;

import com.example.session12.Model.DTO.ProductRequest;
import com.example.session12.Model.Entity.Product;
import com.example.session12.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(
                productService.findAll());
    }

    @PostMapping
    public ResponseEntity<Product> create(
            @RequestBody @Valid ProductRequest request) {

        return ResponseEntity.ok(
                productService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(
            @PathVariable Long id,
            @RequestBody ProductRequest request) {

        return ResponseEntity.ok(
                productService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id) {

        productService.delete(id);

        return ResponseEntity.ok("Deleted");
    }
}
