package com.example.demo_ss6.service;

import com.example.demo_ss6.models.Category;
import com.example.demo_ss6.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    public CategoryRepository categoryRepository;

    public List<Category> getCategoryList(){
        return categoryRepository.findAll();
    }

    public Boolean createCategory(Category c){
        categoryRepository.save(c);
        return true;
    }

    public Boolean deleteCategory(Long id){
        categoryRepository.deleteById(id);
        return true;
    }

    public Boolean updateCategory(Long id){
        Category updateCate = categoryRepository.findById(id).orElseThrow(()->new RuntimeException("không tìm thấy"));
        updateCate.setTitle("update cái này");
        categoryRepository.save(updateCate);
        return true;
    }
}
