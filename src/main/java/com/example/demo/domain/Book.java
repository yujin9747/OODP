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
    public Book(BookBuilder builder){
        this.title = title;
        this.isbn = isbn;
        this.position = position;
//        this.isBorrowed = false;
//        this.isReserved = false;
        this.publisher = publisher;
//         this.library = library;


//        this.enrolledDate = LocalDateTime.now();
//        this.lastModifiedDate = null;
    }

    //builder class
    public static class BookBuilder{
        // required parameters
       // private Long id;
       // private LocalDateTime enrolledDate;
       // private LocalDateTime lastModifiedDate;
        private String title;
        private Long isbn;
        private String position;
        private String publisher;

        // boolean parameters
        private boolean isBorrowed;
        private boolean isReserved;


        public BookBuilder(String title, Long isbn, String position, String publisher){
            this.title = title;
            this.isbn = isbn;
            this.position = position;
//            this.isBorrowed = false; //아래에 해당 변수 별도 함수로 만듦
//            this.isReserved = false;
            this.publisher = publisher;
            //this.library = Library;

//            this.enrolledDate = LocalDateTime.now();
//            this.lastModifiedDate = null;

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
            return new Book(this);
        }

    }

    public void rentBook(RentalInfo rentalInfo){
        this.isBorrowed = true;
//        this.rentalInfoList.add(rentalInfo);
        rentalInfo.setBook(this);
    }
}
