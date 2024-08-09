package com.example.libraryproject.service;

import com.example.libraryproject.model.Book;
import com.example.libraryproject.repository.BookRepo;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.*;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;




@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {


    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepo bookRepo;

   @BeforeEach
   public void setUp() {
       MockitoAnnotations.openMocks(this);
   }

    @Test
    public void createBook_shouldCreateBook() {
        Book newBook = new Book("Test Title", "Test Author", "000",  9);

        when(bookRepo.existsById(newBook.getId())).thenReturn(false);

        when(bookRepo.save(newBook)).thenReturn(newBook);

        Optional<Book> createdBook = bookService.createBook(newBook);

        assertThat(createdBook.isPresent()).isTrue();
        assertThat(createdBook.get()).isEqualTo(newBook);

    }

    @Test
    public void updateBook_shouldUpdateExistingBook() {

        Book existingBook = new Book("Test Title 2", "Test Author 2", "000",  9);
        when(bookRepo.existsById(existingBook.getId())).thenReturn(true);
        when(bookRepo.save(existingBook)).thenReturn(existingBook);

        Book updatedBook = new Book("Updated Title", "Updated Author","000", 9);

        Optional<Book> updatedOptional = bookService.updateBook(updatedBook);

        assertThat(updatedOptional.isPresent()).isTrue();
        assertThat(updatedOptional.get()).isEqualTo(updatedBook);
    }

    @Test
    public void updateBook_shouldReturnEmptyOptional_whenBookIsNull() {

        Optional<Book> updatedOptional = bookService.updateBook(null);

        assertThat(updatedOptional.isEmpty()).isTrue();
    }

    @Test
    public void updateBook_shouldReturnEmptyOptional_whenBookDoesNotExist() {

        Book existingBook = new Book("Test Title 2", "Test Author 2", "001",  9);
        when(bookRepo.existsById(existingBook.getId())).thenReturn(false);

        Book updatedBook = new Book("Updated Title", "Updated Author","001", 9);

        Optional<Book> updatedOptional = bookService.updateBook(updatedBook);

        assertThat(updatedOptional.isEmpty()).isTrue();
    }


}
