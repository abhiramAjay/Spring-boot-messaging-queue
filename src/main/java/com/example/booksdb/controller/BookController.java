package com.example.booksdb.controller;

import com.example.booksdb.model.Books;
import com.example.booksdb.repository.BookRepository;
import com.example.booksdb.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;


    @Autowired
    private BookService bookService;

    @GetMapping("/get-all-books")
    public List<Books> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/get-byid/{id}")
    public ResponseEntity<Books> getBookById(@PathVariable int id) {
        return bookRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete-books/{id}")
    public boolean deleteBooksById(@PathVariable("id") Integer bookId) {
        if (bookRepository.existsById(bookId)) {
            bookRepository.deleteById(bookId);
            return true;
        }
        return false;
    }

    @PostMapping("/add-books")
    public Books create(@RequestBody Books books) {
        return bookRepository.save(books);
    }

    @PutMapping("/update-count/{bookId}")
    public ResponseEntity<Books> updateCount(@PathVariable int bookId) {
        Optional<Books> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Books book = optionalBook.get();
            book.setCount(book.getCount() + 1);
            bookRepository.save(book);
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get-available")
    public List<Books> getAllAvailableBooks() {
        return bookService.getAvailableBooks();
    }

    @GetMapping("/get-unavailable")
    public List<Books> getAllUnavailableBooks() {
        return bookService.GetUnavailableBooks();
    }
}
