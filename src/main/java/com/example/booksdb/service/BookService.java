package com.example.booksdb.service;

import com.example.booksdb.model.Books;

import com.example.booksdb.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Books> getAvailableBooks() {
        return bookRepository.findByCountGreaterThan(0);
    }
    public List<Books>GetUnavailableBooks() {
        return bookRepository.findByCountLessThan(1);
    }
    public List<Books> getAllBooks() {
        return bookRepository.findAll();
    }
}
