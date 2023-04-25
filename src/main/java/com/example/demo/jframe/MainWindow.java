package com.example.demo.jframe;

import com.example.demo.domain.Book;
import com.example.demo.domain.Role;
import com.example.demo.domain.Member;
import com.example.demo.service.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import java.util.List;

public class MainWindow extends JFrame{

    private final MemberService memberService;
    private final BookService bookService;
    private final LibraryService libraryService;
    private final RentalInfoService rentalInfoService;
    private final ReservationInfoService reservationInfoService;

    final int GUEST = -1;
    Button searchBTN;
    Button loginBTN;
    Button adminPageBTN;
    Button logoutBTN;

    JTextField searchBoxField = new JTextField("책 제목을 입력하세요", 20);

    Long memberId;
    Optional<Member> loginedMember = null;

    public MainWindow(MemberService memberService, BookService bookService, LibraryService libraryService, RentalInfoService rentalInfoService, ReservationInfoService reservationInfoService) {

        this.memberService = memberService;
        this.bookService = bookService;
        this.libraryService = libraryService;
        this.rentalInfoService = rentalInfoService;
        this.reservationInfoService = reservationInfoService;

        setTitle("Main"); //창 제목
        setSize(600, 600); //창 사이즈

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        Container c = getContentPane();

        c.setLayout(new FlowLayout());
        c.add(searchBoxField);

        searchBTN = new Button("search");
        searchBTN.setBounds(20, 5, 70, 30);

        add(searchBTN);

        searchBTN.addActionListener(new SearchActionListener());

        if (loginedMember == null) {
            loginBTN = new Button("Login");
            loginBTN.setBounds(300, 300, 70, 30);
            add(loginBTN);
            loginBTN.addActionListener(new LoginActionListener());
        } else {
            logoutBTN = new Button("Logout");
            logoutBTN.setBounds(300, 300, 70, 30);
            logoutBTN.addActionListener(new LogoutActionListener());
            add(logoutBTN);
            if(loginedMember.get().getRole() == Role.ADMIN){
                adminPageBTN = new Button("Admin Page");
                adminPageBTN.setBounds(300, 350, 70, 30);
                add(adminPageBTN);
                //adminPageBTN.addActionListener(new AdminPageActionListener());
            }
        }

        setVisible(true); //보이기

    }

    private class SearchActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            String bookTitle = searchBoxField.getText();
            int indexOfBook = -1;

            List<Book> bookList = bookService.findBooks();
            for(int i=0; i<bookList.size(); i++) {
                if(bookList.get(i).getTitle().equals(bookTitle)) {
                    indexOfBook = i;
                }
            }
            System.out.println("book index: " + indexOfBook);
            //new SearchWindow(memberId, indexOfBook, memberRepository, bookRepository);
            setVisible(false);
        }
    }

    private class AdminPageActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new AdminManagement(bookService);
            setVisible(false);
        }
    }

    private class LoginActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new LoginWindow(memberService);
            setVisible(false);
        }
    }
//
    private class LogoutActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new MainWindow(memberService, bookService, libraryService, rentalInfoService, reservationInfoService);
            setVisible(false);
        }
    }
    public static void main(String[] args) {
//        Library library = new Library(1L, "Handong Global University Library", 200);
//        libraryRepository.getLibraryList().add(library);
//        Book book = new Book(1L, "Introduction to Metaverse", 8972805491L, "510.32 지 474", "좋은 생각", 1L);
//        bookRepository.getBookList().add(book);
//        Student student = new Student(1L, "yujin", Role.STUDENT, "slsddbwls4421", library.getLibraryId(), 22000630);
//        studentRepository.getStudentList().add(student);
//        new MainWindow(0); //생성자 호출
    }


}
