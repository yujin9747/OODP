package com.example.demo.jframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame {

    Button loginBTN;
    Button registerBTN;
    JTextField memberId = new JTextField("회원 아이디를 입력하세요", 20);
    JTextField password = new JTextField("비밀번호를 입력해주세요.", 20);

    public LoginWindow() { //생성자를 만든다.

        setTitle("Login"); //창 제목

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        Container c = getContentPane();

        c.setLayout(new FlowLayout());
        c.add(memberId);
        c.add(password);

        loginBTN = new Button("Login");
        registerBTN = new Button("Register");

        loginBTN.setBounds(20, 5, 70, 30);
        registerBTN.setBounds(120, 5, 100, 30);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };

        loginBTN.addActionListener(listener);
        registerBTN.addActionListener(listener);

        add(loginBTN);
        add(registerBTN);


        setSize(600, 600); //창 사이즈

        setVisible(true); //보이기

    }



    public static void main(String[] args) {
        new LoginWindow(); //생성자 호출
    }
}
