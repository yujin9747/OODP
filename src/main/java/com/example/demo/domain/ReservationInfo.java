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
public class ReservationInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id", nullable = false)
    private Long id;
    private LocalDateTime reservationDate;
    private Role userType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    public ReservationInfo(Long id, Role userType, Member member, Book book){
        this.id = id;
        this.reservationDate = LocalDateTime.now();
        this.userType = userType;
        this.member = member;
        this.book = book;
    }
}
