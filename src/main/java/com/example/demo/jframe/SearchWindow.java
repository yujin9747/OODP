package com.example.demo.jframe;

import com.example.demo.BeanUtil;
import com.example.demo.domain.*;

import com.example.demo.service.BookService;
import com.example.demo.service.MemberService;
import com.example.demo.service.RentalInfoService;
import com.example.demo.service.ReservationInfoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchWindow extends JFrame {

    private final BookService bookService;
    private final MemberService memberService;

    private final RentalInfoService rentalInfoService;
    private final ReservationInfoService reservationInfoService;

    Button checkoutBTN;
    Button returnBTN;
    Button reservationBTN;
    Button backBTN;

    Member loginedMember;
    Book searchedBook;

    ReservationInfo reservationInfo;

    public SearchWindow(Book searchedBook, Member loginedMember){
        this.bookService = BeanUtil.get(BookService.class);
        this.memberService = BeanUtil.get(MemberService.class);
        this.rentalInfoService = BeanUtil.get(RentalInfoService.class);
        this.reservationInfoService = BeanUtil.get(ReservationInfoService.class);
        this.loginedMember = loginedMember;
        this.searchedBook = searchedBook;

        setTitle("Search 결과창"); //창 제목

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        Container c = getContentPane();

        c.setLayout(new GridLayout(9, 1));
        backBTN = new Button("<");
        backBTN.addActionListener(new SearchActionListener());
        add(backBTN);

        JLabel title = new JLabel();
        JLabel position = new JLabel();
        JLabel status = new JLabel();
        JLabel isbn = new JLabel();
        JLabel publisher = new JLabel();
        title.setText("Title : " + searchedBook.getTitle());
        position.setText("Position : " + searchedBook.getPosition());
        if (searchedBook.isBorrowed()) status.setText("Status : 대출중");
        else if (searchedBook.isReserved()) status.setText("Status : 예약중");
        else status.setText("Status : 이용가능");
        isbn.setText("ISBN : " + searchedBook.getIsbn());
        publisher.setText("Publisdher : " + searchedBook.getPublisher());

        add(title);
        add(position);
        add(status);
        add(isbn);
        add(publisher);

        if (loginedMember != null && loginedMember.getRole() != Role.ADMIN) {
            if (!searchedBook.isBorrowed()) {
                checkoutBTN = new Button("대출하기");
                checkoutBTN.setBounds(20, 5, 70, 30);
                checkoutBTN.addActionListener(new SearchActionListener());
                add(checkoutBTN);
            } else {
                returnBTN = new Button("반납하기");
                returnBTN.setBounds(20, 5, 70, 30);

                returnBTN.addActionListener(new SearchActionListener());

                add(returnBTN);
            }


            // Todo : Role.STUDENT, disabled=true인 상태에서 버튼 안나와야 하는데 나옴.
            if (loginedMember.isDisabled() == false && searchedBook.isBorrowed()) {
                this.reservationInfo = reservationInfoService.findOneByBookId(searchedBook.getId());
                if (this.reservationInfo == null) {
                    reservationBTN = new Button("예약하기");
                    reservationBTN.setBounds(20, 5, 70, 30);
                    reservationBTN.addActionListener(new SearchActionListener());

                    add(reservationBTN);
                } else {
                    if (this.reservationInfo.getMember().getId() == loginedMember.getId()) {
                        reservationBTN = new Button("예약취소");
                        reservationBTN.setBounds(20, 5, 70, 30);
                        reservationBTN.addActionListener(new SearchActionListener());

                        add(reservationBTN);
                    } else {
                        JLabel label = new JLabel();
                        label.setText("다른 회원이 예약한 도서입니다.");
                        add(label);
                    }
                }

            }
        }

        setSize(600, 600); //창 사이즈

        setVisible(true); //보이기


    }

    private class SearchActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if(command.equals("<")){
                new MainWindow(loginedMember);
                setVisible(false);
            }
            else if (command.equals("대출하기")) {
                if (loginedMember.getRole() == Role.STUDENT) {
                    rentalInfoService.saveRentalInfo(loginedMember.getId(), searchedBook.getId());
                    JOptionPane.showMessageDialog(null, "대출이 완료되었습니다.");

                    new MainWindow(loginedMember);
                    setVisible(false);
                } else if (loginedMember.getRole() == Role.PROFESSOR) {

                }

            } else if (command.equals("반납하기")) {
                if (loginedMember.getRole() == Role.STUDENT) {
                    rentalInfoService.returnBook(loginedMember.getId(), searchedBook.getId());
                    JOptionPane.showMessageDialog(null, "반납이 완료되었습니다.");

                    new MainWindow(loginedMember);
                    setVisible(false);
                }
                else if(loginedMember.getRole() == Role.PROFESSOR){

                }
            } else if (command.equals("예약하기")) {
                if (loginedMember.getRole() == Role.STUDENT) {
                    reservationInfoService.saveReservationInfo(loginedMember.getId(), searchedBook.getId());
                    JOptionPane.showMessageDialog(null, "예약되었습니다.");

                    new MainWindow(loginedMember);
                    setVisible(false);
                } else if (loginedMember.getRole() == Role.PROFESSOR) {

                }

            } else if (command.equals("예약취소")) {
                if (loginedMember.getRole() == Role.STUDENT) {
                    reservationInfoService.cancelReservation(reservationInfo.getId());
                    JOptionPane.showMessageDialog(null, "예약이 취소되었습니다.");

                    new MainWindow(loginedMember);
                    setVisible(false);
                } else if (loginedMember.getRole() == Role.PROFESSOR) {

                }
            }

        }

    }

}
