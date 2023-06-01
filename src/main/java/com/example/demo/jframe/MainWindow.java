package com.example.demo.jframe;

import com.example.demo.domain.Member;
import com.example.demo.service.*;
import lombok.Getter;
import lombok.Setter;
import javax.swing.*;
import java.awt.*;

@Setter
@Getter
public class MainWindow extends JFrame{

    private MemberService memberService;
    private BookService bookService;
    private RentalInfoService rentalInfoService;

    final int GUEST = -1;
    Button searchBTN;
    Button loginBTN;
    Button adminPageBTN;
    Button logoutBTN;
    Button registerBTN;
    Button userPageBTN;

    JTextField searchBoxField = new JTextField("책 제목을 입력하세요", 20);

    Long memberId;
    Member loginedMember = null;
}
