package com.example.demo.command;

import com.example.demo.domain.Book;
import com.example.demo.domain.Member;
import com.example.demo.service.ReservationInfoService;

public class ReserveCommand implements Command {
    private ReservationInfoService reservationInfoService;
    private Member loginedMember;
    private Book searchedBook;

    public ReserveCommand(ReservationInfoService reservationInfoService, Member member, Book book) {
        this.reservationInfoService = reservationInfoService;
        this.loginedMember = member;
        this.searchedBook = book;
    }

    public void execute() {
        reservationInfoService.reserve(loginedMember, searchedBook);
    }

}
