package com.example.booksdb.config;

import com.example.booksdb.model.Books;
import com.example.booksdb.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class BookReturnConsumer {

    @Autowired
    private BookRepository booksRepository; // Repository to update the book count

    @JmsListener(destination = "bookReturnQueue")
    public void receiveMessage(String message) {
        // Process the message here
        // Assuming the message contains the book ID to update the count
        int bookId = Integer.parseInt(message);
        Books book = booksRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        book.setCount(book.getCount() + 1); // Increment the count
        booksRepository.save(book);
    }
}
