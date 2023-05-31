package com.example.demo.jframe;

import com.example.demo.BeanUtil;
import com.example.demo.builder.builder.SearchWindowBuilder;
import com.example.demo.builder.concreteBuilder.SearchWindowAdminBuilder;
import com.example.demo.builder.director.SearchWindowDirector;
import com.example.demo.domain.Book;
import com.example.demo.domain.Role;
import com.example.demo.domain.Member;
import com.example.demo.service.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class MainWindow extends JFrame{

    private final MemberService memberService;
    private final BookService bookService;

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

    public MainWindow(Member loginedMember) {
        this.memberService = BeanUtil.get(MemberService.class);
        this.bookService = BeanUtil.get(BookService.class);
        this.loginedMember = loginedMember;

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
            if(loginedMember.getRole() == Role.ADMIN){
                adminPageBTN = new Button("Admin Page");
                adminPageBTN.setBounds(300, 350, 70, 30);
                add(adminPageBTN);
                adminPageBTN.addActionListener(new AdminPageActionListener());
            } else {
                userPageBTN = new Button("User Page");
                userPageBTN.setBounds(300, 350, 70, 30);
                add(userPageBTN);
                userPageBTN.addActionListener(new UserPageActionListener());
            }
        }

        setVisible(true); //보이기

    }

    private class SearchActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            String bookTitle = searchBoxField.getText();

            Optional<Book> searchedBook = bookService.findBookByTitle(bookTitle);
            if(searchedBook.isPresent() == false) {
                JOptionPane.showMessageDialog(null, "일치하는 책 정보가 없습니다.");
            }
            else{
                System.out.println("in");
                SearchWindowBuilder searchWindowBuilder;
                if(loginedMember != null){
                    if(loginedMember.getRole() == Role.ADMIN) searchWindowBuilder = new SearchWindowAdminBuilder();
                }
                searchWindowBuilder = new SearchWindowAdminBuilder();
                SearchWindowDirector searchWindowDirector = new SearchWindowDirector(searchWindowBuilder, loginedMember, searchedBook.get(), 0);
                searchWindowDirector.constructSearchWindow();
//                new SearchWindow(searchedBook.get(), loginedMember, 0, false);
            }
        }
    }

    private class AdminPageActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new AdminManagement(loginedMember, null, null);
            setVisible(false);
        }
    }

    private class UserPageActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new UserPageWindow(loginedMember);
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
            new MainWindow(null);
            setVisible(false);
        }
    }

}
