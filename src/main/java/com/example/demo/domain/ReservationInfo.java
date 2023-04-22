package com.example.demo.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class ReservationInfo {
    private Long reservationId;
    private LocalDateTime reservationDate;
    private Enum<Role> userType;
    private Long memberId;
    private Long bookId;

    public ReservationInfo(Long reservationId, Role userType, Long memberId, Long bookId){
        this.reservationId = reservationId;
        this.reservationDate = LocalDateTime.now();
        this.userType = userType;
        this.memberId = memberId;
        this.bookId = bookId;
    }
}
