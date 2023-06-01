package com.example.demo.command;

import com.example.demo.domain.Book;
import com.example.demo.domain.Member;
import com.example.demo.service.RentalInfoService;

import javax.swing.*;

public class ReturnCommand implements Command {
    private RentalInfoService rentalInfoService;
    private Member loginedMember;
    private Book searchedBook;
    private JFrame window;

    public ReturnCommand(RentalInfoService rentalInfoService, Member member, Book book, JFrame window) {
        this.rentalInfoService = rentalInfoService;
        this.loginedMember = member;
        this.searchedBook = book;
        this.window = window;
    }

    public void execute() {
        rentalInfoService.return_book(loginedMember, searchedBook, window);
    }

}
