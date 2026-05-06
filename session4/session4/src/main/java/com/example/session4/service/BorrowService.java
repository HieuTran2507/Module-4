package com.example.session4.service;

import com.example.session4.model.DTO.BorrowResponseDTO;
import com.example.session4.model.entity.Book;
import com.example.session4.model.entity.BorrowTicket;
import com.example.session4.repository.BookRepository;
import com.example.session4.repository.BorrowTicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BorrowService {
    private final BookRepository bookRepository;
    private final BorrowTicketRepository borrowTicketRepository;

    public BorrowService(BookRepository bookRepository,
                         BorrowTicketRepository borrowTicketRepository) {
        this.bookRepository = bookRepository;
        this.borrowTicketRepository = borrowTicketRepository;
    }

    @Transactional
    public BorrowResponseDTO borrowBook(Long bookID, String studentName){
        // 1. kiểm tra book tồn tại
        Book book = bookRepository.findById(bookID)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy sách"));

        // 2. kiểm tra có ai muượn chưa
        Boolean isBorrowed = borrowTicketRepository.existsByBookIdAndStatus(bookID,"borrowed");
        if (isBorrowed)  throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sách đang được mượn");

        // 3. tạo phiếu mượn
        BorrowTicket ticket = new BorrowTicket();
        ticket.setStudentName(studentName);
        ticket.setBook(book);
        borrowTicketRepository.save(ticket);

        // 4. trả về DTO
        return new BorrowResponseDTO(
                studentName,
                book.getTitle(),
                book.getAuthor().getName(),
                ticket.getBorrowDate()
        );
    }
}
