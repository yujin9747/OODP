package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
public class RentalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rentalInfo_id", nullable = false)
    private Long id;
    private LocalDateTime rentalDate;
    private Role userType;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    private boolean isReserved;
    private boolean isOverdue;
    private boolean isReturned;
    private boolean isExtensionAllowed;
    private LocalDateTime returnDueDate;
    private LocalDateTime returnedDate;
    private int overDueDays;

    public RentalInfo(Member member, Book book){
        this.rentalDate = LocalDateTime.now();
        this.userType = member.getRole();
        this.isReserved = false;
        this.isOverdue = false;
        this.isReturned = false;
        this.isExtensionAllowed = true;
        this.returnDueDate = this.rentalDate.plusDays(14L);
        this.returnedDate = null;
        this.overDueDays = 0;

        this.member = member;
        member.getRentalInfoList().add(this);

        this.book = book;
        book.getRentalInfoList().add(this);
        book.setBorrowed(true);
        book.setLastModifiedDate(LocalDateTime.now());
    }

    public RentalInfo returnInfo(){
        this.returnedDate = LocalDateTime.now();
        if(this.returnedDate.isAfter(this.returnDueDate)){
            this.isOverdue = true;
//            this.overDueDays = this.returnedDate - this.returnDueDate;
        }
        else this.isOverdue = false;
        this.isReturned = true;
        return this;
    }

}
