package com.example.session5.controller;

import com.example.session5.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService os;

    @GetMapping("/searchStatus")
    public ResponseEntity<?> searchByStatus(@RequestParam("status") String status){
        return ResponseEntity.ok(os.getOrdersByStatus(status));
    }

    @GetMapping("/searchName")
    public ResponseEntity<?> searchByName(@RequestParam("name") String name){
        return ResponseEntity.ok(os.getOrdersByCustomerName(name));
    }

    @GetMapping("/sort")
    public ResponseEntity<?> sortByField(
            @RequestParam("field") String field,
            @RequestParam("dir") String dir
    ){
        return ResponseEntity.ok(os.getAllOrdersSorted(field,dir));
    }

    @GetMapping("/page")
    public ResponseEntity<?> pagingOrder(
            @RequestParam("page") int page,
            @RequestParam("size") int size){
        return ResponseEntity.ok(os.getOrderPage(page,size));
    }

    @GetMapping("/get-high-value")
    public ResponseEntity<?> getHighValueOrders(){
        return ResponseEntity.ok(os.getHighOrdersValue());
    }

    @GetMapping("/findAllAndPagination")
    public ResponseEntity<?> findAllAndPagination(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ){
        Pageable p = PageRequest.of(page,size);
        return ResponseEntity.ok(os.findAllAndPagination(p));
    }

    @GetMapping("/filter")
    public ResponseEntity<?> paginationAndFilter(
            @RequestParam(required = false) String status,
            @RequestParam(required = false)BigDecimal minPrice,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(defaultValue = "createdAt") String sort
            ){
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sort).descending()
        );

        return ResponseEntity.ok(
                os.filterOrder(
                        status,
                        minPrice,
                        pageable
                )
        );

    }
}
