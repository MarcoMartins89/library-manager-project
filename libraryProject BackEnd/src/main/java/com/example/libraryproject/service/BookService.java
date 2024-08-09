package com.example.libraryproject.service;

import com.example.libraryproject.model.Book;
import com.example.libraryproject.repository.BookRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {


    // Injecting the interface into my service, to enable future testing necessities

    @Autowired
    private BookRepo bookRepo;


    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    // Using Optional in this case, to prevent a case where a book is not found

    public Optional<Book> getBookById(Integer id) {
        return bookRepo.findById(id);
    }

    public Optional<Book> getBookByTitle(String title) {
        return bookRepo.findByTitleIgnoreCase(title);
    }

    public Optional<Book> getBookByAuthor(String author) {
        return bookRepo.findByAuthorIgnoreCase(author);
    }

    // Logging framework to enable logging statements to my methods

    private static final Logger log = LoggerFactory.getLogger(BookService.class);

    // Adding logging statements for debugging and monitoring purposes
    // Adding Optional for allowing a better error handling, more flexibility in personalized responses to errors.

    public Optional<Book> createBook(Book book) {
        if (bookRepo.existsById(book.getId())) {
            log.info("Book already exists in database: {}", book.getId());
            return Optional.empty();
        }
        log.info("Saving book: {}", book);
        return Optional.of(bookRepo.save(book));
    }

    public Optional<Book> updateBook(Book book) {
        if (book == null) {
            log.info("Provided book object is null");
            return Optional.empty();
        }

        if (!bookRepo.existsById(book.getId())) {
            log.info("The book you're searching for doesn't exist in database: {}", book.getId());
            return Optional.empty();
        }

        log.info("Updating book information: {}", book.getId());
        return Optional.of(bookRepo.save(book));
    }


    public void deleteBookById(Integer id) {
        log.info("Deleting book: {}", id);
        bookRepo.deleteById(id);
    }

}
