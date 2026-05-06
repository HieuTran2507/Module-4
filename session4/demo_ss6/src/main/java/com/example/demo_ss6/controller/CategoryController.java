package com.example.demo_ss6.controller;

import com.example.demo_ss6.models.Category;
import com.example.demo_ss6.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getCategoryList());
    }

    @PostMapping
    public String addCategory(){
        Category newCate = new Category();
        newCate.setTitle("máy móc");
        categoryService.createCategory(newCate);
        return "thêm thành công";
    }

    @GetMapping("/{id}")
    public String deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return "xóa thành công";
    }

    @PatchMapping("/{id}")
    public String updateCategory(@PathVariable Long id){
        categoryService.updateCategory(id);
        return "update thành công";
    }
}
