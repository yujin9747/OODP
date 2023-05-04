package com.example.demo.jframe;

import com.example.demo.BeanUtil;
import com.example.demo.domain.Member;
import com.example.demo.service.BookService;
import com.example.demo.service.LibraryService;
import com.example.demo.service.MemberService;
import com.example.demo.service.RentalInfoService;
import com.example.demo.domain.Book;
import com.example.demo.domain.RentalInfo;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UserPageWindow extends JFrame {
    private final MemberService memberService;
    private final LibraryService libraryService;
    private final BookService bookService;
    private JList bookList;
    private DefaultListModel<String> model;

    private final RentalInfoService rentalInfoService;

    public UserPageWindow (Member loginedMember) {
        this.memberService = BeanUtil.get(MemberService.class);
        this.libraryService = BeanUtil.get(LibraryService.class);
        this.bookService = BeanUtil.get(BookService.class);
        this.rentalInfoService = BeanUtil.get(RentalInfoService.class);

        setTitle("User Page");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        bookList = new JList<>();
        model = new DefaultListModel<>();

        String[] columnNames = {"title", "Estimated return date", " ", " "};

        List<RentalInfo> rentalInfoList = rentalInfoService.findRentalInfosByMemberId(loginedMember.getId());

        Object[][] data = new Object[rentalInfoList.size()][];
        for (int i = 0; i < rentalInfoList.size(); i++) {
            RentalInfo rentalInfo = rentalInfoList.get(i);

            Book book = rentalInfo.getBook();
            String returnDueDate = rentalInfo.getReturnDueDate().toString();
            data[i] = new Object[]{book.getTitle(), returnDueDate, new JButton("return"), new JButton("renew")};
        }

        JTable bookTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(bookTable);
        c.add(scrollPane);

        setSize(600, 600); //창 사이즈

        setVisible(true); //보이기
    }

}
