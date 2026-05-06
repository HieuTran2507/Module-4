package com.example.session4.controller;

import com.example.session4.model.DTO.BookRequest;
import com.example.session4.model.entity.Book;
import com.example.session4.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bs;

    // post book
    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody BookRequest request){
        try {
            return ResponseEntity.ok(bs.createBook(request));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // get all books
    @GetMapping
    public ResponseEntity<?> getAllBooks(){
        List<Book> books = bs.getAllBooks();
        if (books.isEmpty()) return ResponseEntity.badRequest().body("danh sách trống");
        else return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookByID(@PathVariable Long id){
        try {
            return ResponseEntity.ok(bs.getBookByID(id));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/searchByTitle")
    public ResponseEntity<?> searchByTitle(@RequestParam("keyword") String keyword){
        List<Book> searchedBook = bs.searchByTitle(keyword);
        if (searchedBook.isEmpty()) return ResponseEntity.badRequest().body("không tìm thấy sách");
        else return ResponseEntity.ok(searchedBook);
    }

    @GetMapping("/findByAvgPrice")
    public ResponseEntity<List<Book>> findByAvgPrice(){
        return ResponseEntity.ok(bs.searchByAvgPrice());
    }

    @GetMapping("/statisticByAuthor")
    public ResponseEntity<?> statisticByAuthor(){
        return ResponseEntity.ok(bs.statisticByAuthor());
    }
}
