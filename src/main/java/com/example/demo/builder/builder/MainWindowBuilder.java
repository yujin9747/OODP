package com.example.demo.builder.builder;

import com.example.demo.BeanUtil;
import com.example.demo.actionListener.MainActionListener;
import com.example.demo.actionListener.SearchActionListener;
import com.example.demo.builder.concreteAdminBuilder.AdminManagementDefaultBuilder;
import com.example.demo.builder.concreteLoginBuilder.LoginWindowLoginBuilder;
import com.example.demo.builder.concreteLoginBuilder.LoginWindowRegisterBuilder;
import com.example.demo.builder.concreteMainBuilder.MainWindowNullBuilder;
import com.example.demo.builder.director.AdminManagementWindowDirector;
import com.example.demo.builder.director.LoginWindowDirector;
import com.example.demo.builder.director.MainWindowDirector;
import com.example.demo.domain.Member;
import com.example.demo.jframe.*;
import com.example.demo.service.BookService;
import com.example.demo.service.MemberService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public abstract class MainWindowBuilder {

    protected MainWindow mainWindow;
    public MainWindow getMainWindow(){
        return mainWindow;
    }

    public void createNewMainWindowProduct(){
        mainWindow = new MainWindow();
    }
    public void buildLoginedMember(Member loginedMember){
        mainWindow.setLoginedMember(loginedMember);
    }
    public void buildSearchBoxField(){
        mainWindow.setSearchBoxField(new JTextField("책 제목을 입력하세요", 20));
    }
    public void buildDefaultSetting(){
        mainWindow.setTitle("Main"); //창 제목
        mainWindow.setSize(600, 600); //창 사이즈
        mainWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
        mainWindow.getSearchBTN().addActionListener(new MainActionListener(mainWindow));
    }
    public void buildVisible(){
        mainWindow.setVisible(true);
    }
    public abstract void builWindowButton();
    public class AdminPageActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AdminManagementDefaultBuilder builder = new AdminManagementDefaultBuilder();
            AdminManagementWindowDirector director = new AdminManagementWindowDirector(builder, mainWindow.getLoginedMember(), null, null);
            director.constructAdminManagementWindow();
            mainWindow.setVisible(false);
        }
    }

    public class UserPageActionListener implements ActionListener {
        private Member loginedMember;

        public UserPageActionListener(Member loginedMember){
            this.loginedMember = loginedMember;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            new UserPageWindow(loginedMember);
            mainWindow.setVisible(false);
        }
    }

    public class LoginActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LoginWindowLoginBuilder builder = new LoginWindowLoginBuilder();
            LoginWindowDirector director = new LoginWindowDirector(builder);
            director.constructLoginWindow();
            mainWindow.setVisible(false);
        }
    }
    public class RegisterActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LoginWindowRegisterBuilder builder = new LoginWindowRegisterBuilder();
            LoginWindowDirector director = new LoginWindowDirector(builder);
            director.constructLoginWindow();
            mainWindow.setVisible(false);
        }
    }

    public class LogoutActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MainWindowNullBuilder builder = new MainWindowNullBuilder();
            MainWindowDirector director = new MainWindowDirector(builder, null);
            director.constructMainWindow();
            mainWindow.setVisible(false);
        }
    }

}
