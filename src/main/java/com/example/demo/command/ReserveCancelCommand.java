package com.example.demo.command;

import com.example.demo.domain.Book;
import com.example.demo.domain.Member;
import com.example.demo.domain.ReservationInfo;
import com.example.demo.service.ReservationInfoService;

public class ReserveCancelCommand implements Command {
    private ReservationInfoService reservationInfoService;
    private ReservationInfo reservationInfo;
    private Member loginedMember;
    private Book searchedBook;

    public ReserveCancelCommand(ReservationInfoService reservationInfoService, ReservationInfo reservationInfo, Member member, Book book) {
        this.reservationInfoService = reservationInfoService;
        this.reservationInfo = reservationInfo;
        this.loginedMember = member;
        this.searchedBook = book;
    }

    public void execute() {
        reservationInfoService.reserveCancel(reservationInfo, loginedMember, searchedBook);
    }

}
