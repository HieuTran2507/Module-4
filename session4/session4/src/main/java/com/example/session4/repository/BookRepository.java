package com.example.session4.repository;

import com.example.session4.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Objects;

public interface BookRepository extends JpaRepository<Book,Long> {
    // 1. query method
    List<Book> findByTitleContaining(String keyword);

    // 2. JPQL: giá > giá trung bình
    @Query("SELECT b FROM Book b where b.price > (SELECT AVG(b2.price) FROM Book b2)")
    List<Book> findBooksHigherThanAvgPrice();

    // 3. native sql : thống kê số sách theo author
    @Query(value = """
        SELECT a.name, COUNT(b.id)
        FROM books b 
        JOIN authors a ON b.author_id = a.id
        GROUP BY a.name""",nativeQuery = true)
    List<Object[]> countBooksByAuthor();
}
