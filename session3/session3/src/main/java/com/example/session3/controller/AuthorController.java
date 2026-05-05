package com.example.session3.controller;

import com.example.session3.model.Author;
import com.example.session3.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors(){
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @PostMapping
    public ResponseEntity<Author> addAuthor(@RequestBody Author a){
        Author newAuthor = authorService.createAuthor(a);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAuthor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthorByID(@PathVariable int id){
        Author a = authorService.getAuthorByID(id);
        if (a!=null) return ResponseEntity.ok(a);
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("không thấy tác giả nào với id nafy");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable int id, @RequestBody Author a){
        Author updateAuthor = authorService.updateAuthor(id,a);
        if (updateAuthor!=null) return ResponseEntity.ok(updateAuthor);
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy tác giả với ID này");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable int id){
        Boolean chk = authorService.deleteAuthor(id);
        if (chk) return ResponseEntity.ok("xóa thành công");
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("không tìm thấy tác giả với id này");
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filterAuthor(@RequestParam String name){
        List<Author> filterAuthor =  authorService.filterAuthor(name);
        if (filterAuthor.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("không có kết quả phù hợp");
        else return ResponseEntity.ok(filterAuthor);
    }
}
