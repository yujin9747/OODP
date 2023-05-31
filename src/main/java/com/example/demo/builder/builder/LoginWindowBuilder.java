package com.example.demo.builder.builder;

import com.example.demo.BeanUtil;
import com.example.demo.actionListener.AdminSearchActionListener;
import com.example.demo.actionListener.LoginActionListener;
import com.example.demo.domain.Library;
import com.example.demo.jframe.LoginWindow;
import com.example.demo.service.*;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public abstract class LoginWindowBuilder {
    protected LoginWindow loginWindow;

    public LoginWindow getLoginWindow() { return loginWindow; }

    public void createNewLoginWindowProduct() { loginWindow = new LoginWindow(); }

    public void buildDependencyInjection() {
        loginWindow.setMemberService(BeanUtil.get(MemberService.class));
        loginWindow.setLibraryService(BeanUtil.get(LibraryService.class));
        loginWindow.setBookService(BeanUtil.get(BookService.class));
        loginWindow.setRentalInfoService(BeanUtil.get(RentalInfoService.class));
        loginWindow.setReservationInfoService(BeanUtil.get(ReservationInfoService.class));
    }

    public void buildLoginLibrary() {
        loginWindow.setLoginLibrary(loginWindow.getLibraryService().findOne(1L));
    }


    public void buildWindowTitle(){
        loginWindow.setTitle("Login");
    }

    public void buildWindowDefaultSetting(){
        loginWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
        loginWindow.setLayout(null);
    }

    public void buildContainer(){
        Container c = loginWindow.getContentPane();
        c.setLayout(new FlowLayout());
    }

    public void buildJTextField(){
        loginWindow.setStudentIdField(new JTextField("22000630", 20));
        loginWindow.setPasswordField(new JTextField("slsddbwls4421", 20));
        addIdPasswordField();
    }

    public void buildFinished() {
        loginWindow.setSize(600, 600); //창 사이즈
        loginWindow.setVisible(true); //보이기
    }

    private void addIdPasswordField() {
        loginWindow.add(loginWindow.getStudentIdField());
        loginWindow.add(loginWindow.getPasswordField());
    }

    public void buildBackButtonBuilder(int loginOrRegister){
        loginWindow.setBackBTN(new Button("<"));
        loginWindow.getBackBTN().addActionListener(new LoginActionListener(loginWindow, loginOrRegister));
        loginWindow.add(loginWindow.getBackBTN());
        loginWindow.add(new JLabel(" "));
    }

    public abstract void buildBackButton();
    public abstract void buildFunctionButton();

}
