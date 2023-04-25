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
    private Enum<Role> userType;
    private Long memberId;
    private Long bookId;

    public ReservationInfo(Long id, Role userType, Long memberId, Long bookId){
        this.id = id;
        this.reservationDate = LocalDateTime.now();
        this.userType = userType;
        this.memberId = memberId;
        this.bookId = bookId;
    }
}
