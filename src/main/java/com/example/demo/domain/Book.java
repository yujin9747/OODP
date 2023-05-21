package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.type.SpecialOneToOneType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@RequiredArgsConstructor

// book builder interface



// book class
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

    @ManyToOne
    @JoinColumn(name = "library_id")
    private Library library;

    @OneToMany(mappedBy = "book")
    private List<RentalInfo> rentalInfoList = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    private List<ReservationInfo> reservationInfoList = new ArrayList<>();

//    public Book(String title, Long isbn, String position, String publisher, Library library){
    public Book(String title,Long isbn,String position,String publisher,Library library){
        this.title = title;
        this.isbn = isbn;
        this.position = position;
        this.publisher = publisher;
        this.library = library;


        this.enrolledDate = LocalDateTime.now();
        this.lastModifiedDate = null;
    }
    //builder class
    public static class BookBuilder{
        private String title;
        private Long isbn;
        private String position;
        private String publisher;

        private Library library;

        // boolean parameters
        private boolean isBorrowed;
        private boolean isReserved;
        public BookBuilder title(String title){
            this.title = title;
            return this;
        }
        public BookBuilder isbn(Long isbn){
            this.isbn = isbn;
            return this;
        }
        public BookBuilder position(String position){
            this.position = position;
            return this;
        }
        public BookBuilder publisher(String publisher){
            this.publisher = publisher;
            return this;
        }
        public BookBuilder library(Library library){
            this.library = library;
            return this;
        }
        public BookBuilder setIsBorrowed(boolean isBorrowed) {
            this.isBorrowed = isBorrowed;
            return this;
        }

        public BookBuilder setIsReserved(boolean isReserved) {
            this.isReserved = isReserved;
            return this;
        }

        public Book build() {
            return new Book(title,isbn,position,publisher,library);
        }

    }

    public void rentBook(RentalInfo rentalInfo){
        this.isBorrowed = true;
//        this.rentalInfoList.add(rentalInfo);
        rentalInfo.setBook(this);
    }
}
