package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "library_id")
    private Library library;

    @OneToMany(mappedBy = "book")
    private List<RentalInfo> rentalInfoList = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    private List<ReservationInfo> reservationInfoList = new ArrayList<>();

    public Book(String title, Long isbn, String position, String publisher, Library library){
        this.title = title;
        this.isbn = isbn;
        this.position = position;
        this.isBorrowed = false;
        this.isReserved = false;
        this.publisher = publisher;
        this.library = library;

        this.enrolledDate = LocalDateTime.now();
        this.lastModifiedDate = null;
    }
}
