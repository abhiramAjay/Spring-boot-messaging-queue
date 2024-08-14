package com.example.booksdb.repository;

import com.example.booksdb.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookRepository extends JpaRepository<Books, Integer> {
    List<Books> findByCountGreaterThan(Integer count);
    List<Books> findByCountLessThan(Integer count);
}

