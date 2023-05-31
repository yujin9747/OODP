package com.example.demo.jframe;

import com.example.demo.command.*;
import com.example.demo.builder.builder.SearchWindowBuilder;
import com.example.demo.builder.concreteBuilder.SearchWindowAdminBuilder;
import com.example.demo.builder.concreteBuilder.SearchWindowAdminEditBuilder;
import com.example.demo.builder.director.SearchWindowDirector;
import com.example.demo.domain.*;

import com.example.demo.domain.request.BookUpdateForm;
import com.example.demo.service.BookService;
import com.example.demo.service.MemberService;
import com.example.demo.service.RentalInfoService;
import com.example.demo.service.ReservationInfoService;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

@Setter
@Getter
public class SearchWindow extends JFrame {

    private BookService bookService;
    private MemberService memberService;
    private RentalInfoService rentalInfoService;
    private ReservationInfoService reservationInfoService;

    Button checkoutBTN;
    Button returnBTN;
    Button reservationBTN;
    Button backBTN;
    Button editBTN;
    Button deleteBTN;

    Member loginedMember;
    Book searchedBook;
    ReservationInfo reservationInfo;
    Integer beforePage; // 1 : adminPage

    JTextField titleInput;
    JTextField isbnInput;
    JTextField positionInput;
    JTextField publisherInput;
    ButtonWithCommand buttonWithCommand;

    JLabel titleLabel;
    JLabel position;
    JLabel status;
    JLabel isbn;
    JLabel publisher;

    JLabel titleInfo;
    JLabel positionInfo;
    JLabel statusInfo;
    JLabel isbnInfo;
    JLabel publisherInfo;

//    public SearchWindow(){}
//
//    public SearchWindow(Book searchedBook, Member loginedMember, Integer beforePage, boolean editMode){
//        this.bookService = BeanUtil.get(BookService.class);
//        this.memberService = BeanUtil.get(MemberService.class);
//        this.rentalInfoService = BeanUtil.get(RentalInfoService.class);
//        this.reservationInfoService = BeanUtil.get(ReservationInfoService.class);
//        this.loginedMember = loginedMember;
//        ;this.searchedBook = searchedBook;
//        this.beforePage = beforePage;
//        this.buttonWithCommand = new ButtonWithCommand(new InitCommand());
//
//        setTitle("Search 결과창"); //창 제목
//
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setLayout(null);
//
//        Container c = getContentPane();
//
//        c.setLayout(new GridLayout(9, 2));
//        backBTN = new Button("<");
//        backBTN.addActionListener(new AdminSearchActionListener());
//        if(editMode == false) {
//            add(backBTN);
//            add(new JLabel(" "));
//        }
//
//        JLabel title = new JLabel("Title : ");
//        JLabel position = new JLabel("Position : ");
//        JLabel status = new JLabel("Status :");
//        JLabel isbn = new JLabel("ISBN : ");
//        JLabel publisher = new JLabel("Publisdher : ");
//
//        if(editMode == false){
//            JLabel titleInfo = new JLabel(searchedBook.getTitle());
//            JLabel positionInfo = new JLabel(searchedBook.getPosition());
//            JLabel statusInfo = (searchedBook.isBorrowed()) ? new JLabel("대출중") : (searchedBook.isReserved()) ? new JLabel("예약중") : new JLabel("이용가능");
//            JLabel isbnInfo = new JLabel(String.valueOf(searchedBook.getIsbn()));
//            JLabel publisherInfo = new JLabel(searchedBook.getPublisher());
//
//            add(title);
//            add(titleInfo);
//            add(position);
//            add(positionInfo);
//            add(status);
//            add(statusInfo);
//            add(isbn);
//            add(isbnInfo);
//            add(publisher);
//            add(publisherInfo);
//        }
//        else {
//            titleInput = new JTextField(searchedBook.getTitle());
//            positionInput = new JTextField(searchedBook.getPosition());
//            JLabel statusInfo = (searchedBook.isBorrowed()) ? new JLabel("대출중") : (searchedBook.isReserved()) ? new JLabel("예약중") : new JLabel("이용가능");
//            isbnInput = new JTextField(searchedBook.getIsbn().toString());
//            publisherInput = new JTextField(searchedBook.getPublisher());
//            add(title);
//            add(titleInput);
//            add(position);
//            add(positionInput);
//            add(status);
//            add(statusInfo);
//            add(isbn);
//            add(isbnInput);
//            add(publisher);
//            add(publisherInput);
//        }
//
//        if (loginedMember != null && loginedMember.getRole() != Role.ADMIN) {
//            if (!rentalInfoService.isTheBookBorrowed(searchedBook)) {
//                Command checkoutCommand = new CheckoutCommand(rentalInfoService, loginedMember, searchedBook);
//                buttonWithCommand.setCommand(checkoutCommand);
//                checkoutBTN = new Button("대출하기");
//                checkoutBTN.setBounds(20, 5, 70, 30);
//                checkoutBTN.addActionListener(new SearchActionListener());
//                add(checkoutBTN);
//            } else {
//                RentalInfo _rentalInfo = rentalInfoService.findOneByMemberIdAndBookId(loginedMember.getId(), searchedBook.getId());
//                if (_rentalInfo != null && !_rentalInfo.isReturned()) {
//                    Command returnCommand = new ReturnCommand(rentalInfoService, loginedMember, searchedBook);
//                    buttonWithCommand.setCommand(returnCommand);
//                    returnBTN = new Button("반납하기");
//                    returnBTN.setBounds(20, 5, 70, 30);
//
//                    returnBTN.addActionListener(new SearchActionListener());
//
//                    add(returnBTN);
//                } else {
//                    this.reservationInfo = reservationInfoService.findOneByBookId(searchedBook.getId());
//                    if (this.reservationInfo == null) {
//                        Command reserveCommand = new ReserveCommand(reservationInfoService, loginedMember, searchedBook);
//                        buttonWithCommand.setCommand(reserveCommand);
//                        reservationBTN = new Button("예약하기");
//                        reservationBTN.setBounds(20, 5, 70, 30);
//                        reservationBTN.addActionListener(new SearchActionListener());
//
//                        add(reservationBTN);
//                    } else {
//                        if (this.reservationInfo.getMember().getId() == loginedMember.getId()) {
//                            Command reserveCancelCommand = new ReserveCancelCommand(reservationInfoService, reservationInfo, loginedMember, searchedBook);
//                            buttonWithCommand.setCommand(reserveCancelCommand);
//                            reservationBTN = new Button("예약취소");
//                            reservationBTN.setBounds(20, 5, 70, 30);
//                            reservationBTN.addActionListener(new SearchActionListener());
//
//                            add(reservationBTN);
//                        } else {
//                            JLabel label = new JLabel();
//                            label.setText("다른 회원이 예약한 도서입니다.");
//                            add(label);
//                        }
//                    }
//                }
//            }
//
//        } else if(loginedMember != null && loginedMember.getRole() == Role.ADMIN){
//            editBTN = new Button();
//            if ((editMode)) {
//                editBTN.setLabel("수정완료");
//            } else {
//                editBTN.setLabel("수정하기");
//            }
//            editBTN.setBounds(20, 5, 70, 30);
//            deleteBTN = new Button("삭제하기");
//            deleteBTN.setBounds(20, 5, 70, 30);
//
//            editBTN.addActionListener(new AdminSearchActionListener());
//            deleteBTN.addActionListener(new AdminSearchActionListener());
//
//            add(editBTN);
//            if(editMode == false) add(deleteBTN);
//        }
//
//        setSize(600, 600); //창 사이즈
//
//        setVisible(true); //보이기
//
//
//    }
//
//    private class SearchActionListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            buttonWithCommand.pressed();
//        }
//    }
//
//    private class AdminSearchActionListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            String command = e.getActionCommand();
//
//            if(command.equals("<")){
//                if (beforePage != 1)
//                    new MainWindow(loginedMember);
//                else
//                    new AdminManagement(loginedMember, null, null);
//                setVisible(false);
//            } else if (command.equals("수정하기")) {
//                new SearchWindow(searchedBook, loginedMember, beforePage, true);
//                setVisible(false);
//            } else if (command.equals("삭제하기")) {
//                bookService.deleteBook(searchedBook.getTitle());
//                setVisible(false);
//                new AdminManagement(loginedMember, null, null);
//                JOptionPane.showMessageDialog(null, searchedBook.getTitle() + "책 삭제가 완료되었습니다.");
//            } else if (command.equals("수정완료")) {
//                BookUpdateForm bookUpdateForm = new BookUpdateForm.BookUpdateFormBuilder()
//                        .title(titleInput.getText())
//                        .isbn(isbnInput.getText())
//                        .position(positionInput.getText())
//                        .publisher(publisherInput.getText())
//                        .build();
//                Optional<Book> book = bookService.updateBook(searchedBook.getTitle(), bookUpdateForm);
//                new SearchWindow(book.get(), loginedMember, beforePage, false);
//                setVisible(false);
//            }
//        }
//
//    }


}
