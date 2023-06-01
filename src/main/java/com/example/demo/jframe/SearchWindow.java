package com.example.demo.jframe;

import com.example.demo.command.*;
import com.example.demo.domain.*;

import com.example.demo.service.BookService;
import com.example.demo.service.MemberService;
import com.example.demo.service.RentalInfoService;
import com.example.demo.service.ReservationInfoService;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Setter
@Getter
public class SearchWindow extends JFrame {

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

}
