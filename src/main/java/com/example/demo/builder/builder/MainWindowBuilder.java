package com.example.demo.builder.builder;

import com.example.demo.BeanUtil;
import com.example.demo.domain.Member;
import com.example.demo.jframe.*;
import com.example.demo.actionListener.SearchActionListener;
import com.example.demo.service.BookService;
import com.example.demo.service.MemberService;
import com.example.demo.service.RentalInfoService;
import com.example.demo.service.ReservationInfoService;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public abstract class MainWindowBuilder {

    protected MainWindow mainWindow;
    private Member loginedMember;
    public MainWindow getMainWindow(){
        return mainWindow;
    }

    public void createNewMainWindowProduct(Member loginedMember){
        mainWindow = new MainWindow(loginedMember);
    }
    public void buildDependencyInjection() {
        mainWindow.setMemberService(BeanUtil.get(MemberService.class));
        mainWindow.setBookService(BeanUtil.get(BookService.class));
    }
    public void buildLoginedMember(Member loginedMember){
        mainWindow.setLoginedMember(loginedMember);
    }
    public void buildSearchBoxField(){
        mainWindow.setSearchBoxField(new JTextField("책 제목을 입력하세요", 20));
    }
    public void buildWindowTitle(){
        mainWindow.setTitle("Main");
    }
    public void buildWindowSize(){
        mainWindow.setSize(600, 600);
    }
    public void buildWindowDefaultSetting(){
        mainWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainWindow.setLayout(null);
    }
    public void buildContainer(){
        Container c = mainWindow.getContentPane();
        c.setLayout(new FlowLayout());
        c.add(mainWindow.getSearchBoxField());
    }
    public void buildSearchBTN(){
        mainWindow.setSearchBTN(new Button("search"));
        mainWindow.setBounds(20, 5, 70, 30);
        mainWindow.add(mainWindow.getSearchBTN());
        mainWindow.getSearchBTN().addActionListener(new SearchActionListener());
    }
    public void buildVisible(){
        mainWindow.setVisible(true);
    }
    public abstract void builWindowButton();
    public class AdminPageActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new AdminManagement(loginedMember, null, null);
            mainWindow.setVisible(false);
        }
    }

    public class UserPageActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new UserPageWindow(loginedMember);
            mainWindow.setVisible(false);
        }
    }

    public class LoginActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new LoginWindow(0);
            mainWindow.setVisible(false);
        }
    }
    public class RegisterActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new LoginWindow(1);
            mainWindow.setVisible(false);
        }
    }

    public class LogoutActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new MainWindow(null);
            mainWindow.setVisible(false);
        }
    }




}
