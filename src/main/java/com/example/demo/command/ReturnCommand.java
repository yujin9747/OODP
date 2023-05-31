package com.example.demo.command;

import com.example.demo.domain.Book;
import com.example.demo.domain.Member;
import com.example.demo.service.RentalInfoService;

public class ReturnCommand implements Command {
    private RentalInfoService rentalInfoService;
    private Member loginedMember;
    private Book searchedBook;

    public ReturnCommand(RentalInfoService rentalInfoService, Member member, Book book) {
        this.rentalInfoService = rentalInfoService;
        this.loginedMember = member;
        this.searchedBook = book;
    }

    public void execute() {
        rentalInfoService.return_book(loginedMember, searchedBook);
    }

}
