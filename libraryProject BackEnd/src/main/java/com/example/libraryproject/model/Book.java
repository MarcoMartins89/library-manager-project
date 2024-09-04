package com.example.libraryproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;



@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Book {


    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String  author;
    private String isbn;
    private Date publishedDate;
    private Integer price;

    public Book(String title, String author, String isbn, Integer price) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;

    }

}
