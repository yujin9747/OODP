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
    //builder interface
    public interface Builder{

        public Builder title(String title);

        public Builder isbn(Long isbn);

        public Builder position(String position);

        public Builder publisher(String publisher);

        public Builder library(Library library);

        public Book build();

    }
    //builder class
    public static class BookBuilder implements Builder{
        private String title;
        private Long isbn;
        private String position;
        private String publisher;

        private Library library;

        // boolean parameters
        private boolean isBorrowed;
        private boolean isReserved;
        @Override
        public Builder title(String title){
            this.title = title;
            return this;
        }
        @Override
        public Builder isbn(Long isbn){
            this.isbn = isbn;
            return this;
        }
        @Override
        public Builder position(String position){
            this.position = position;
            return this;
        }
        @Override
        public Builder publisher(String publisher){
            this.publisher = publisher;
            return this;
        }
        @Override
        public Builder library(Library library){
            this.library = library;
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
