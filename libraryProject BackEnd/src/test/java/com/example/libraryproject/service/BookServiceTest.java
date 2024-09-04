package com.example.libraryproject.service;

import com.example.libraryproject.model.Book;
import com.example.libraryproject.repository.BookRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookRepo bookRepo;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllBooks_shouldReturnListOfBooks() {
        List<Book> books = Arrays.asList(
                new Book("Title 1", "Author 1", "123", 10),
                new Book("Title 2", "Author 2", "456", 20)
        );
        when(bookRepo.findAll()).thenReturn(books);

        ResponseEntity<List<Book>> response = bookService.getAllBooks();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(books);
    }

    @Test
    public void getBookById_shouldReturnBookIfFound() {
        Book book = new Book("Title", "Author", "123", 10);
        book.setId(1);
        when(bookRepo.findById(1)).thenReturn(Optional.of(book));

        Optional<Book> foundBook = bookService.getBookById(1);

        assertThat(foundBook.isPresent()).isTrue();
        assertThat(foundBook.get()).isEqualTo(book);
    }

    @Test
    public void getBookById_shouldReturnEmptyIfNotFound() {
        when(bookRepo.findById(1)).thenReturn(Optional.empty());

        Optional<Book> foundBook = bookService.getBookById(1);

        assertThat(foundBook.isEmpty()).isTrue();
    }

    @Test
    public void getBookByTitle_shouldReturnBookIfFound() {
        Book book = new Book("Title", "Author", "123", 10);
        when(bookRepo.findByTitleIgnoreCase("Title")).thenReturn(Optional.of(book));

        Optional<Book> foundBook = bookService.getBookByTitle("Title");

        assertThat(foundBook.isPresent()).isTrue();
        assertThat(foundBook.get()).isEqualTo(book);
    }

    @Test
    public void getBookByAuthor_shouldReturnBookIfFound() {
        Book book = new Book("Title", "Author", "123", 10);
        when(bookRepo.findByAuthorIgnoreCase("Author")).thenReturn(Optional.of(book));

        Optional<Book> foundBook = bookService.getBookByAuthor("Author");

        assertThat(foundBook.isPresent()).isTrue();
        assertThat(foundBook.get()).isEqualTo(book);
    }

    @Test
    public void createBook_shouldCreateBookIfNotExists() {
        Book newBook = new Book("New Title", "New Author", "001", 15);
        when(bookRepo.existsById(newBook.getId())).thenReturn(false);
        when(bookRepo.save(newBook)).thenReturn(newBook);

        Optional<Book> createdBook = bookService.createBook(newBook);

        assertThat(createdBook.isPresent()).isTrue();
        assertThat(createdBook.get()).isEqualTo(newBook);
    }

    @Test
    public void createBook_shouldReturnEmptyOptionalIfBookExists() {
        Book existingBook = new Book("Title", "Author", "001", 15);
        existingBook.setId(1);
        when(bookRepo.existsById(1)).thenReturn(true);

        Optional<Book> createdBook = bookService.createBook(existingBook);

        assertThat(createdBook.isEmpty()).isTrue();
    }

    @Test
    public void updateBook_shouldUpdateExistingBook() {
        Book existingBook = new Book("Title", "Author", "123", 15);
        existingBook.setId(1);
        when(bookRepo.existsById(1)).thenReturn(true);
        when(bookRepo.save(existingBook)).thenReturn(existingBook);

        Optional<Book> updatedBook = bookService.updateBook(existingBook);

        assertThat(updatedBook.isPresent()).isTrue();
        assertThat(updatedBook.get()).isEqualTo(existingBook);
    }

    @Test
    public void updateBook_shouldReturnEmptyOptionalIfBookDoesNotExist() {
        Book book = new Book("Title", "Author", "123", 15);
        book.setId(1);
        when(bookRepo.existsById(1)).thenReturn(false);

        Optional<Book> updatedBook = bookService.updateBook(book);

        assertThat(updatedBook.isEmpty()).isTrue();
    }

    @Test
    public void deleteBookById_shouldCallRepositoryDelete() {
        int bookId = 1;

        bookService.deleteBookById(bookId);

        verify(bookRepo, times(1)).deleteById(bookId);
    }
}
