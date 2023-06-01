package com.example.demo.builder.concreteSearchBuilder;

import com.example.demo.actionListener.SearchActionListener;
import com.example.demo.builder.builder.SearchWindowBuilder;
import com.example.demo.command.*;
import com.example.demo.domain.Book;
import com.example.demo.domain.Member;
import com.example.demo.domain.RentalInfo;
import com.example.demo.service.RentalInfoService;
import com.example.demo.service.ReservationInfoService;

import javax.swing.*;
import java.awt.*;

public class SearchWindowUserBuilder extends SearchWindowBuilder {

    @Override
    public void buildBackButton() {
        super.buildBackButtonBuilder(0);
    }

    @Override
    public void buildFunctionButton() {
        Book searchedBook = searchWindow.getSearchedBook();
        Member loginedMember = searchWindow.getLoginedMember();
        RentalInfoService rentalInfoService = searchWindow.getRentalInfoService();
        ReservationInfoService reservationInfoService = searchWindow.getReservationInfoService();
        ButtonWithCommand buttonWithCommand = new ButtonWithCommand(new InitCommand());

        if (!rentalInfoService.isTheBookBorrowed(searchedBook)) {
            Command checkoutCommand = new CheckoutCommand(rentalInfoService, loginedMember, searchedBook, searchWindow);
            buttonWithCommand.setCommand(checkoutCommand);
            searchWindow.setCheckoutBTN(new Button("대출하기"));
            searchWindow.getCheckoutBTN().setBounds(20, 5, 70, 30);
            searchWindow.getCheckoutBTN().addActionListener(new SearchActionListener(buttonWithCommand));
            searchWindow.add(searchWindow.getCheckoutBTN());
        } else {
            RentalInfo _rentalInfo = rentalInfoService.findOneByMemberIdAndBookId(loginedMember.getId(), searchedBook.getId());
            if (_rentalInfo != null && !_rentalInfo.isReturned()) {
                Command returnCommand = new ReturnCommand(rentalInfoService, loginedMember, searchedBook, searchWindow);
                buttonWithCommand.setCommand(returnCommand);
                searchWindow.setReturnBTN(new Button("반납하기"));
                searchWindow.getReturnBTN().setBounds(20, 5, 70, 30);
                searchWindow.getReturnBTN().addActionListener(new SearchActionListener(buttonWithCommand));
                searchWindow.add(searchWindow.getReturnBTN());
            } else {
                searchWindow.setReservationInfo(reservationInfoService.findOneByBookId(searchedBook.getId()));
                if (searchWindow.getReservationInfo() == null) {
                    Command reserveCommand = new ReserveCommand(reservationInfoService, loginedMember, searchedBook);
                    buttonWithCommand.setCommand(reserveCommand);
                    searchWindow.setReservationBTN(new Button("예약하기"));
                    searchWindow.getReservationBTN().setBounds(20, 5, 70, 30);
                    searchWindow.getReservationBTN().addActionListener(new SearchActionListener(buttonWithCommand));
                    searchWindow.add(searchWindow.getReservationBTN());
                } else {
                    if (searchWindow.getReservationInfo().getMember().getId() == loginedMember.getId()) {
                        Command reserveCancelCommand = new ReserveCancelCommand(reservationInfoService, searchWindow.getReservationInfo(), loginedMember, searchedBook);
                        buttonWithCommand.setCommand(reserveCancelCommand);
                        searchWindow.setReservationBTN(new Button("예약취소"));
                        searchWindow.getReservationBTN().setBounds(20, 5, 70, 30);
                        searchWindow.getReservationBTN().addActionListener(new SearchActionListener(buttonWithCommand));
                        searchWindow.add(searchWindow.getReservationBTN());
                    } else {
                        JLabel label = new JLabel("다른 회원이 예약한 도서입니다.");
                        searchWindow.add(label);
                    }
                }
            }
        }
    }

    @Override
    public void buildBookInfoLabel() {
        super.buildBookInfoLabelBuilder();
    }
}
