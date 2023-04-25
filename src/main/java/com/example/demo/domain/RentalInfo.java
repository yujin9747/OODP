package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class RentalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rentalInfo_id", nullable = false)
    private Long id;
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

    public RentalInfo(Long id, Role userType, Long memberId, Long bookId){
        this.id = id;
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
