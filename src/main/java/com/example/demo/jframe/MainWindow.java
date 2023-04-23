package com.example.demo.jframe;

import com.example.demo.domain.Book;
import com.example.demo.domain.Library;
import com.example.demo.domain.Role;
import com.example.demo.domain.Student;
import com.example.demo.domain.Member;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame{
    Button searchBTN;

    Button loginBTN;
    JTextField searchBoxField = new JTextField("책 제목을 입력하세요", 20);;

    public MainWindow() { //생성자를 만든다.

        setTitle("Main"); //창 제목
        setSize(600, 600); //창 사이즈

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        Container c = getContentPane();

        c.setLayout(new FlowLayout());
        c.add(searchBoxField);

        loginBTN = new Button("Login");
        loginBTN.setBounds(300, 300, 70, 30);

        searchBTN = new Button("search");
        searchBTN.setBounds(20, 5, 70, 30);

        add(searchBTN);
        add(loginBTN);

        searchBTN.addActionListener(new SearchActionListener());

        setVisible(true); //보이기

    }

    private class SearchActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new LoginWindow(); // SearchWindow로 변경 예정
            setVisible(false);
        }
    }

    private class LoginActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new LoginWindow();
            setVisible(false);
        }
    }
    public static void main(String[] args) {

        new MainWindow(); //생성자 호출
    }

}
