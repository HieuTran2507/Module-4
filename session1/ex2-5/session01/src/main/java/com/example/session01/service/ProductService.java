package com.example.session01.service;

import com.example.session01.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private List<Product> products = new ArrayList<>();

    // Constructor: Tạo dữ liệu giả khi khởi động
    public ProductService() {
        products.add(new Product(1, "Quần jeans", 1500.0));
        products.add(new Product(2, "Áo thun", 1200.0));
        products.add(new Product(3, "Áo mưa", 25.0));
    }

    public List<Product> getAllProduct(){
        return this.products;
    }

    public Product addProduct(Product p){
        this.products.add(p);
        return p;
    }

    public Product updateProduct(int id, Product newProduct){
        for (Product p : products){
            if (p.getId() == id){
                p.setName(newProduct.getName());
                p.setPrice(newProduct.getPrice());
                return p;
            }
        }
        return null;
    }

    public Boolean deleteProduct(int id){
        return products.removeIf(p -> p.getId()==id);
    }
}
