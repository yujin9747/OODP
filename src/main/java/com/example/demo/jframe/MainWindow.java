package com.example.demo.jframe;

import com.example.demo.domain.Book;
import com.example.demo.domain.Library;
import com.example.demo.domain.Role;
import com.example.demo.domain.Student;
import com.example.demo.domain.Member;
import com.example.demo.repository.StudentRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame{

    static StudentRepository studentRepository = new StudentRepository();

    Button searchBTN;
    Button loginBTN;
    Button adminPageBTN;
    Button logoutBTN;

    JTextField searchBoxField = new JTextField("책 제목을 입력하세요", 20);

    int indexOfMember;
    Member loginedMember;

    public MainWindow(int indexOfMember) { //생성자를 만든다.
        this.indexOfMember = indexOfMember;
        this.loginedMember = (indexOfMember == -1) ? null : studentRepository.getStudentList().get(indexOfMember);

        setTitle("Main"); //창 제목
        setSize(600, 600); //창 사이즈

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        Container c = getContentPane();

        c.setLayout(new FlowLayout());
        c.add(searchBoxField);

        searchBTN = new Button("search");
        searchBTN.setBounds(20, 5, 70, 30);

        add(searchBTN);

        if (indexOfMember == -1) {
            loginBTN = new Button("Login");
            loginBTN.setBounds(300, 300, 70, 30);
            add(loginBTN);
        } else {
            logoutBTN = new Button("Logout");
            logoutBTN.setBounds(300, 300, 70, 30);
            add(logoutBTN);
            if(loginedMember.getRole() != Role.ADMIN){
                adminPageBTN = new Button("Admin Page");
                adminPageBTN.setBounds(300, 350, 70, 30);
                add(adminPageBTN);
            }
        }

        searchBTN.addActionListener(new SearchActionListener());

        setVisible(true); //보이기

    }

    private class SearchActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new SearchWindow(indexOfMember, 0);
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

        new MainWindow(-1); //생성자 호출
    }

}
