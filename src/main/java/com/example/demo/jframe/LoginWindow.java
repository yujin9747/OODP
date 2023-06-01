package com.example.demo.jframe;
import com.example.demo.domain.*;
import javax.swing.*;
import java.awt.*;
import java.util.Optional;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginWindow extends JFrame {
    Button studentBTN;
    Button adminBTN;
    Button backBTN;
    JTextField studentIdField = new JTextField("22000630", 20);
    JTextField passwordField = new JTextField("slsddbwls4421", 20);
    int loginOrRegister;
    Role loginOrRegisterRole;
    Optional<Library> loginLibrary;   // login하려는 도서관 -> 한동대학교로 상정하고 개발함

}

