package com.example.demo.command;

import com.example.demo.domain.Member;
import com.example.demo.domain.Book;
import com.example.demo.service.RentalInfoService;


public class CheckoutCommand implements Command{
    private RentalInfoService rentalInfoService;
    private Member loginedMember;
    private Book searchedBook;

    public CheckoutCommand(RentalInfoService rentalInfoService, Member member, Book book) {
        this.rentalInfoService = rentalInfoService;
        this.loginedMember = member;
        this.searchedBook = book;
    }

    public void execute() {
        rentalInfoService.checkout(loginedMember, searchedBook);
    }

}
