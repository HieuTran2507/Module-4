package com.example.session5.service;

import com.example.session5.model.dto.OrderSummary;
import com.example.session5.model.dto.PaginationResponse;
import com.example.session5.model.entity.Order;
import com.example.session5.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OrderService {
    @Autowired
    private OrderRepository or;

    public Object getOrdersByStatus(String status){
        List<Order> orders = or.findByStatus(status);
        if (orders.isEmpty()) return "không tìm thấy đơn hàng với trạng thái: " + status;
        return orders;
    }

    public Object getOrdersByCustomerName(String name){
        List<Order> orders = or.findByCustomerNameContaining(name);
        if (orders.isEmpty()) return "không tìm thấy đơn hàng với tên khách hàng: " + name;
        return orders;
    }

    public Object getAllOrdersSorted(String field, String dir){
        Sort sort;
        if (dir.equalsIgnoreCase("desc")) sort = Sort.by(field).descending();
        else if (dir.equalsIgnoreCase("asc")) sort = Sort.by(field).ascending();
        else return "chọn sắp xếp theo 'desc' hoặc 'asc'";
        return or.findAll(sort);
    }

    public Page<Order> getOrderPage(int page, int size){
        PageRequest pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return or.findAll(pageable);
    }

    public List<Order> getHighOrdersValue(){
        return or.findHighValueOrders();
    }

    public PaginationResponse findAllAndPagination(Pageable pageable){
        Page<OrderSummary> pageData = or.findAllAndPagination(pageable);
        PaginationResponse response = new PaginationResponse();
        response.setData(pageData.getContent());
        response.setCurrentPage(pageData.getNumber());
        response.setTotalElement(pageData.getTotalElements());
        response.setTotalPage(pageData.getTotalPages());
        return response;
    }

    public Page<OrderSummary> filterOrder(
            String status,
            BigDecimal minPrice,
            Pageable pageable
    ){
        return or.filterOrder(status,minPrice,pageable);
    }

}
