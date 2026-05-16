package com.example.session5.repository;

import com.example.session5.model.dto.OrderSummary;
import com.example.session5.model.entity.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatus(String status);
    List<Order> findByCustomerNameContaining(String name);
    @Query("""
        SELECT o
        FROM Order o
        WHERE o.totalPrice > (
            SELECT AVG(o2.totalPrice)
            FROM Order o2
            WHERE MONTH(o2.createdAt) = MONTH(CURRENT_DATE)
                AND YEAR(o2.createdAt) = YEAR(CURRENT_DATE)
        )
    """)
    List<Order> findHighValueOrders();

    @Query("""
        SELECT new com.example.session5.model.dto.OrderSummary(
                o.orderCode,
                o.customerName,
                o.totalPrice
                )
        FROM Order o
        """)
    Page<OrderSummary> findAllAndPagination(Pageable pageable);

    @Query("""
        SELECT new com.example.session5.model.dto.OrderSummary(
            o.orderCode,
            o.customerName,
            o.totalPrice
            )
        FROM Order o
        WHERE (:status is null or o.status=:status)
            AND (:minPrice is null or o.totalPrice >= :minPrice)
    """)
    Page<OrderSummary> filterOrder(
            @Param("status") String status,
            @Param("minPrice") BigDecimal minPrice,
            Pageable pageable
    );
}
