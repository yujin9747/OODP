package com.example.demo.actionListener;

import com.example.demo.command.ButtonWithCommand;
import com.example.demo.domain.Book;
import com.example.demo.domain.Role;
import com.example.demo.domain.request.BookUpdateForm;
import com.example.demo.jframe.AdminManagement;
import com.example.demo.jframe.MainWindow;
import com.example.demo.jframe.SearchWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class SearchActionListener implements ActionListener {

    private ButtonWithCommand buttonWithCommand;

    public SearchActionListener(ButtonWithCommand buttonWithCommand) {
        this.buttonWithCommand = buttonWithCommand;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        buttonWithCommand.pressed();
//        String command = e.getActionCommand();
//
//        if(command.equals("<")){
//            if (beforePage != 1)
//                new MainWindow(loginedMember);
//            else
//                new AdminManagement(loginedMember, null, null);
//            setVisible(false);
//        }
//        else if (command.equals("대출하기")) {
//            if (loginedMember.getRole() == Role.STUDENT) {
//                if ((loginedMember.getLibrary().getId() == searchedBook.getLibrary().getId()) || loginedMember.isExternalLibraryPermission()) {
//                    rentalInfoService.saveRentalInfo(loginedMember.getId(), searchedBook.getId());
//                    JOptionPane.showMessageDialog(null, "대출이 완료되었습니다.");
//                } else {
//                    JOptionPane.showMessageDialog(null, "외부도서에 대한 접근이 허가되지 않았습니다.");
//                }
//
//
//                new MainWindow(loginedMember);
//                setVisible(false);
//            } else if (loginedMember.getRole() == Role.PROFESSOR) {
//
//            }
//
//        } else if (command.equals("반납하기")) {
//            if (loginedMember.getRole() == Role.STUDENT) {
//                rentalInfoService.returnBook(loginedMember.getId(), searchedBook.getId());
//                JOptionPane.showMessageDialog(null, "반납이 완료되었습니다.");
//
//                new MainWindow(loginedMember);
//                setVisible(false);
//            }
//            else if(loginedMember.getRole() == Role.PROFESSOR){
//
//            }
//        } else if (command.equals("예약하기")) {
//            if (loginedMember.getRole() == Role.STUDENT) {
//                if ((loginedMember.getLibrary().getId() == searchedBook.getLibrary().getId()) || loginedMember.isExternalLibraryPermission()) {
//                    reservationInfoService.saveReservationInfo(loginedMember.getId(), searchedBook.getId());
//                    JOptionPane.showMessageDialog(null, "예약되었습니다.");
//                } else {
//                    JOptionPane.showMessageDialog(null, "외부도서에 대한 접근이 허가되지 않았습니다.");
//                }
//                new MainWindow(loginedMember);
//                setVisible(false);
//            } else if (loginedMember.getRole() == Role.PROFESSOR) {
//            }
//
//        } else if (command.equals("예약취소")) {
//            if (loginedMember.getRole() == Role.STUDENT) {
//                reservationInfoService.cancelReservation(reservationInfo.getId());
//                JOptionPane.showMessageDialog(null, "예약이 취소되었습니다.");
//
//                new MainWindow(loginedMember);
//                setVisible(false);
//            } else if (loginedMember.getRole() == Role.PROFESSOR) {
//
//            }
//        } else if (command.equals("수정하기")) {
//            new SearchWindow(searchedBook, loginedMember, beforePage, true);
//            setVisible(false);
//        } else if (command.equals("삭제하기")) {
//            bookService.deleteBook(searchedBook.getTitle());
//            setVisible(false);
//            new AdminManagement(loginedMember, null, null);
//            JOptionPane.showMessageDialog(null, searchedBook.getTitle() + "책 삭제가 완료되었습니다.");
//        } else if (command.equals("수정완료")) {
//            BookUpdateForm bookUpdateForm = new BookUpdateForm.BookUpdateFormBuilder()
//                    .title(titleInput.getText())
//                    .isbn(isbnInput.getText())
//                    .position(positionInput.getText())
//                    .publisher(publisherInput.getText())
//                    .build();
//            Optional<Book> book = bookService.updateBook(searchedBook.getTitle(), bookUpdateForm);
//            new SearchWindow(book.get(), loginedMember, beforePage, false);
//            setVisible(false);
//        }
//    }
    }

}