package com.example.libraryproject.controller;

import com.example.libraryproject.model.Book;
import com.example.libraryproject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public ResponseEntity <List<Book>> getAllBooks() {
        return bookService.getAllBooks();
    }

// Handling the Optional return value of the getBookById from the service

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        Optional<Book> bookOptional = bookService.getBookById(id);
        return bookOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/books/{title}")
    public ResponseEntity<Book> getBookByTitle(@PathVariable String title) {
        Optional<Book> bookOptional = bookService.getBookByTitle(title);
        return bookOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/books/{author}")
    public ResponseEntity<Book> getBookByAuthor(@PathVariable String author) {
        Optional<Book> bookOptional = bookService.getBookByAuthor(author);
        return bookOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    //Need to customize error message
    @RequestMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Optional<Book> createdBookOptional = bookService.createBook(book);
        return createdBookOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.internalServerError().build());
    }


    @PutMapping("/book/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book book) {
        Optional<Book> updatedBookOptional = bookService.updateBook(book);
        return updatedBookOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable Integer id) {
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }
}
