package com.example.demo.jframe;

import com.example.demo.BeanUtil;
import com.example.demo.domain.Book;
import com.example.demo.domain.Role;
import com.example.demo.domain.Member;
import com.example.demo.service.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import java.util.List;

public class MainWindow extends JFrame{

    private final MemberService memberService;
    private final BookService bookService;

    final int GUEST = -1;
    Button searchBTN;
    Button loginBTN;
    Button adminPageBTN;
    Button logoutBTN;
    Button registerBTN;

    JTextField searchBoxField = new JTextField("책 제목을 입력하세요", 20);

    Long memberId;
    Optional<Member> loginedMember = null;

    public MainWindow() {
        this.memberService = BeanUtil.get(MemberService.class);
        this.bookService = BeanUtil.get(BookService.class);

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

        searchBTN.addActionListener(new SearchActionListener());

        if (loginedMember == null) {
            loginBTN = new Button("Login");
            loginBTN.setBounds(300, 300, 70, 30);
            add(loginBTN);
            loginBTN.addActionListener(new LoginActionListener());

            registerBTN = new Button("Register");
            registerBTN.setBounds(300, 300, 70, 30);
            add(registerBTN);
            registerBTN.addActionListener(new RegisterActionListener());
        } else {
            logoutBTN = new Button("Logout");
            logoutBTN.setBounds(300, 300, 70, 30);
            logoutBTN.addActionListener(new LogoutActionListener());
            add(logoutBTN);
            if(loginedMember.get().getRole() == Role.ADMIN){
                adminPageBTN = new Button("Admin Page");
                adminPageBTN.setBounds(300, 350, 70, 30);
                add(adminPageBTN);
                adminPageBTN.addActionListener(new AdminPageActionListener());
            }
        }

        setVisible(true); //보이기

    }

    private class SearchActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            String bookTitle = searchBoxField.getText();
            int indexOfBook = -1;

            List<Book> bookList = bookService.findBooks();
            for(int i=0; i<bookList.size(); i++) {
                if(bookList.get(i).getTitle().equals(bookTitle)) {
                    indexOfBook = i;
                }
            }
            System.out.println("book index: " + indexOfBook);
            new SearchWindow(1, indexOfBook);
            setVisible(false);
        }
    }

    private class AdminPageActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new AdminManagement();
            setVisible(false);
        }
    }

    private class LoginActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new LoginWindow(0);
            setVisible(false);
        }
    }
    private class RegisterActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new LoginWindow(1);
            setVisible(false);
        }
    }

    private class LogoutActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new MainWindow();
            setVisible(false);
        }
    }

}
