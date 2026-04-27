package com.example.session01.controller;

import com.example.session01.model.Product;
import com.example.session01.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // đánh dấu nơi xử lý API
@RequestMapping("/api/products") // // Đường dẫn gốc cho tất cả API trong class này
public class ProductController {
    @Autowired // Tiêm (Inject) ProductService vào Controller
    private ProductService productService;

    // GET: Lấy danh sách
    @GetMapping
    public List<Product> getProducts(){
        return productService.getAllProduct();
    }

    // POST: thêm mới
    @PostMapping
    public Product createProduct(@RequestBody Product p){
        return productService.addProduct(p);
    }

    // PUT : cập nhật theo id
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product p){
        return productService.updateProduct(id, p);
    }

    // DELETE : xóa theo id
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id){
        Boolean chk = productService.deleteProduct(id);
        if (chk) return "xoas thành công id "+id;
        else return "xóa không thành công";
    }
}
