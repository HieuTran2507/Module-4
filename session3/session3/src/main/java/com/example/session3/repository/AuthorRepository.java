package com.example.session3.repository;

import com.example.session3.model.entity.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository {
    private List<Author> authors = new ArrayList<>();

    public AuthorRepository() {
        authors.add(new Author("Nguyễn Văn A", "a@gmail.com"));
        authors.add(new Author("Nguyễn Văn B", "b@gmail.com"));
        authors.add(new Author("Nguyễn Văn C", "c@gmail.com"));
    }

    public List<Author> findAll(){
        return this.authors;
    }

    public void save(Author a){
        authors.add(a);
    }

    public Optional<Author> findByID(int id){
        return authors.stream().filter(a->a.getId()==id).findFirst();
    }

    public void updateAuthor(int id, Author newAuthor){
        findByID(id).ifPresent(a->{
            a.setName(newAuthor.getName());
            a.setEmail(newAuthor.getEmail());
        });
    }

    public Boolean delete(int id){
        return authors.removeIf(a->a.getId()==id);
    }

    public List<Author> filterAuthor(String name){
        if (name == null) return authors; // hoặc return List.of();

        return authors.stream()
                .filter(a -> a.getName() != null &&
                        a.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }
}
