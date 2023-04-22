package com.example.demo.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class Book {
    private Long bookId;
    private String title;
    private LocalDateTime enrolledDate;
    private LocalDateTime lastModifiedDate;
    private Long isbn;
    private String position;
    private boolean isBorrowed;
    private boolean isReserved;
    private String publisher;
    private Long libraryId;

    public Book(Long bookId, String title, Long isbn, String position, String publisher, Long libraryId){
        this.bookId = bookId;
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
