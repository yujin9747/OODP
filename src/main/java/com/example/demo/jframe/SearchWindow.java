package com.example.demo.jframe;

import com.example.demo.BeanUtil;
import com.example.demo.domain.*;

import com.example.demo.domain.request.BookUpdateForm;
import com.example.demo.service.BookService;
import com.example.demo.service.MemberService;
import com.example.demo.service.RentalInfoService;
import com.example.demo.service.ReservationInfoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class SearchWindow extends JFrame {

    private final BookService bookService;
    private final MemberService memberService;

    private final RentalInfoService rentalInfoService;
    private final ReservationInfoService reservationInfoService;

    Button checkoutBTN;
    Button returnBTN;
    Button reservationBTN;
    Button backBTN;
    Button editBTN;
    Button deleteBTN;

    Member loginedMember;
    Book searchedBook;
    Integer beforePage; // 1 : adminPage
    JTextField titleInput;
    JTextField isbnInput;
    JTextField positionInput;
    JTextField publisherInput;

    ReservationInfo reservationInfo;

    public SearchWindow(Book searchedBook, Member loginedMember, Integer beforePage, boolean editMode){
        this.bookService = BeanUtil.get(BookService.class);
        this.memberService = BeanUtil.get(MemberService.class);
        this.rentalInfoService = BeanUtil.get(RentalInfoService.class);
        this.reservationInfoService = BeanUtil.get(ReservationInfoService.class);
        this.loginedMember = loginedMember;
        this.searchedBook = searchedBook;
        this.beforePage = beforePage;

        setTitle("Search 결과창"); //창 제목

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        Container c = getContentPane();

        c.setLayout(new GridLayout(9, 2));
        backBTN = new Button("<");
        backBTN.addActionListener(new SearchActionListener());
        if(editMode == false) {
            add(backBTN);
            add(new JLabel(" "));
        }

        JLabel title = new JLabel("Title : ");
        JLabel position = new JLabel("Position : ");
        JLabel status = new JLabel("Status :");
        JLabel isbn = new JLabel("ISBN : ");
        JLabel publisher = new JLabel("Publisdher : ");

        if(editMode == false){
            JLabel titleInfo = new JLabel(searchedBook.getTitle());
            JLabel positionInfo = new JLabel(searchedBook.getPosition());
            JLabel statusInfo = (searchedBook.isBorrowed()) ? new JLabel("대출중") : (searchedBook.isReserved()) ? new JLabel("예약중") : new JLabel("이용가능");
            JLabel isbnInfo = new JLabel(String.valueOf(searchedBook.getIsbn()));
            JLabel publisherInfo = new JLabel(searchedBook.getPublisher());

            add(title);
            add(titleInfo);
            add(position);
            add(positionInfo);
            add(status);
            add(statusInfo);
            add(isbn);
            add(isbnInfo);
            add(publisher);
            add(publisherInfo);
        }
        else {
            titleInput = new JTextField(searchedBook.getTitle());
            positionInput = new JTextField(searchedBook.getPosition());
            JLabel statusInfo = (searchedBook.isBorrowed()) ? new JLabel("대출중") : (searchedBook.isReserved()) ? new JLabel("예약중") : new JLabel("이용가능");
            isbnInput = new JTextField(searchedBook.getIsbn().toString());
            publisherInput = new JTextField(searchedBook.getPublisher());
            add(title);
            add(titleInput);
            add(position);
            add(positionInput);
            add(status);
            add(statusInfo);
            add(isbn);
            add(isbnInput);
            add(publisher);
            add(publisherInput);
        }

        if (loginedMember != null && loginedMember.getRole() != Role.ADMIN) {
            if (!searchedBook.isBorrowed()) {
                checkoutBTN = new Button("대출하기");
                checkoutBTN.setBounds(20, 5, 70, 30);
                checkoutBTN.addActionListener(new SearchActionListener());
                add(checkoutBTN);
            } else {
                if (rentalInfoService.findOneByMemberIdAndBookId(loginedMember.getId(), searchedBook.getId()) != null) {
                    returnBTN = new Button("반납하기");
                    returnBTN.setBounds(20, 5, 70, 30);

                    returnBTN.addActionListener(new SearchActionListener());

                    add(returnBTN);
                }
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
        } else if(loginedMember != null && loginedMember.getRole() == Role.ADMIN){
            editBTN = new Button();
            if ((editMode)) {
                editBTN.setLabel("수정완료");
            } else {
                editBTN.setLabel("수정하기");
            }
            editBTN.setBounds(20, 5, 70, 30);
            deleteBTN = new Button("삭제하기");
            deleteBTN.setBounds(20, 5, 70, 30);

            editBTN.addActionListener(new SearchActionListener());
            deleteBTN.addActionListener(new SearchActionListener());

            add(editBTN);
            if(editMode == false) add(deleteBTN);
        }

        setSize(600, 600); //창 사이즈

        setVisible(true); //보이기


    }

    private class SearchActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if(command.equals("<")){
                if (beforePage != 1)
                    new MainWindow(loginedMember);
                else
                    new AdminManagement(loginedMember, null, null);
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
            } else if (command.equals("수정하기")) {
                new SearchWindow(searchedBook, loginedMember, beforePage, true);
                setVisible(false);
            } else if (command.equals("삭제하기")) {
                bookService.deleteBook(searchedBook.getTitle());
                setVisible(false);
                new AdminManagement(loginedMember, null, null);
                JOptionPane.showMessageDialog(null, searchedBook.getTitle() + "책 삭제가 완료되었습니다.");
            } else if (command.equals("수정완료")) {
                BookUpdateForm bookUpdateForm = new BookUpdateForm.BookUpdateFormBuilder()
                        .title(titleInput.getText())
                        .isbn(isbnInput.getText())
                        .position(positionInput.getText())
                        .publisher(publisherInput.getText())
                        .build();
                Optional<Book> book = bookService.updateBook(searchedBook.getTitle(), bookUpdateForm);
                new SearchWindow(book.get(), loginedMember, beforePage, false);
                setVisible(false);
            }
        }

    }

}
