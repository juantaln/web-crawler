package com.portafolio.web_crawler.controllers;

import com.portafolio.web_crawler.entities.Book;
import com.portafolio.web_crawler.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
    
        List<Book> books = bookRepository.findAll();
        
        return ResponseEntity.ok(books);
    }
}