package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", nullable = false)
    private Long id;
    private String title;
    private LocalDateTime enrolledDate;
    private LocalDateTime lastModifiedDate;
    private Long isbn;
    private String position;
    private boolean isBorrowed;
    private boolean isReserved;
    private String publisher;
    private Long libraryId;

    public Book(Long id, String title, Long isbn, String position, String publisher, Long libraryId){
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.position = position;
        this.isBorrowed = false;
        this.isReserved = false;
        this.publisher = publisher;
        this.libraryId = libraryId;

        this.enrolledDate = LocalDateTime.now();
        this.lastModifiedDate = null;
    }
}
