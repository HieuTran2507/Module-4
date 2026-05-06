package com.example.session4.service;

import com.example.session4.model.DTO.BookRequest;
import com.example.session4.model.entity.Author;
import com.example.session4.model.entity.Book;
import com.example.session4.repository.AuthorRepository;
import com.example.session4.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private AuthorRepository ar;
    @Autowired
    private BookRepository br;

    // post book
    public Book createBook(BookRequest request){
        Author author = ar.findById(request.getAuthorID())
                .orElseThrow(()->new RuntimeException("tác giả không tồn tại"));

        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setPrice(request.getPrice());
        book.setAuthor(author);

        return br.save(book);
    }

    // get book
    public List<Book> getAllBooks(){
        return br.findAll();
    }

    // get book by id
    public Book getBookByID(Long id){
        return br.findById(id)
                .orElseThrow(()->new RuntimeException("không tìm thấy sách"));
    }

    // search by title
    public List<Book> searchByTitle(String keyword){
        return br.findByTitleContaining(keyword);
    }

    // search books giá > giá trung bình
    public List<Book> searchByAvgPrice(){
        return br.findBooksHigherThanAvgPrice();
    }

    // thống kê số sách theo tác giả
    public List<Object[]> statisticByAuthor(){
        return br.countBooksByAuthor();
    }
}
