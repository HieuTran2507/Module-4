package com.example.session4.repository;

import com.example.session4.model.entity.BorrowTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowTicketRepository extends JpaRepository<BorrowTicket,Long> {
    Boolean existsByBookIdAndStatus(Long bookID, String status);
}
