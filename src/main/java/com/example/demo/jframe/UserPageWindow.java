package com.example.demo.jframe;

import com.example.demo.BeanUtil;
import com.example.demo.service.BookService;
import com.example.demo.service.LibraryService;
import com.example.demo.service.MemberService;
import com.example.demo.service.RentalInfoService;

import javax.swing.*;

public class UserPageWindow extends JFrame {
    private final MemberService memberService;
    private final LibraryService libraryService;
    private final BookService bookService;

    private final RentalInfoService rentalInfoService;

    public UserPageWindow () {
        this.memberService = BeanUtil.get(MemberService.class);
        this.libraryService = BeanUtil.get(LibraryService.class);
        this.bookService = BeanUtil.get(BookService.class);
        this.rentalInfoService = BeanUtil.get(RentalInfoService.class);

        setTitle("User Page");



        setSize(600, 600); //창 사이즈

        setVisible(true); //보이기
    }

}
