package com.example.session3.service;

import com.example.session3.model.entity.Author;
import com.example.session3.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    public Author createAuthor(Author a){
        authorRepository.save(a);
        return a;
    }

    public Author getAuthorByID(int id){
        return authorRepository.findByID(id).orElse(null);
    }

    public Author updateAuthor(int id, Author a){
        if (authorRepository.findByID(id).isPresent()){
            authorRepository.updateAuthor(id,a);
            return a;
        }
        return null;
    }

    public Boolean deleteAuthor(int id){
        return authorRepository.delete(id);
    }

    public List<Author> filterAuthor(String name){
        return authorRepository.filterAuthor(name);
    }
}
