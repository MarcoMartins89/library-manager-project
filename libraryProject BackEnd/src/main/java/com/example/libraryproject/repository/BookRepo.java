package com.example.libraryproject.repository;

import com.example.libraryproject.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepo extends JpaRepository<Book, Integer> {
    Optional<Book> findByTitleIgnoreCase(String title);

    Optional<Book> findByAuthorIgnoreCase(String author);

}
