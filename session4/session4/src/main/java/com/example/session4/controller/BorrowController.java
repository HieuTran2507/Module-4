package com.example.session4.controller;

import com.example.session4.model.DTO.BorrowResponseDTO;
import com.example.session4.service.BorrowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/V1/borrow")
public class BorrowController {
    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @PostMapping("/{bookID}")
    public BorrowResponseDTO borrowBook(@PathVariable Long bookID, @RequestParam("studentName") String studentName){
        return borrowService.borrowBook(bookID,studentName);
    }
}
