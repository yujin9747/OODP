package com.example.demo.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class RentalInfo {
    private Long rentalInfoId;
    private LocalDateTime rentalDate;
    private Enum<Role> userType;
    private Long memberId;
    private Long bookId;
    private boolean isReserved;
    private boolean isOverdue;
    private boolean isReturned;
    private boolean isExtensionAllowed;
    private LocalDateTime returnDueDate;
    private LocalDateTime returnedDate;
    private int overDueDays;

    public RentalInfo(Long rentalInfoId, Role userType, Long memberId, Long bookId){
        this.rentalInfoId = rentalInfoId;
        this.rentalDate = LocalDateTime.now();
        this.userType = userType;
        this.memberId = memberId;
        this.bookId = bookId;
        this.isReserved = false;
        this.isOverdue = false;
        this.isReserved = false;
        this.isExtensionAllowed = true;
        this.returnDueDate = this.rentalDate.plusDays(14L);
        this.returnedDate = null;
        this.overDueDays = 0;
    }

}
