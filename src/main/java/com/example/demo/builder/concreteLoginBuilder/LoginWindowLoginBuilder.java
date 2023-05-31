package com.example.demo.builder.concreteLoginBuilder;

import com.example.demo.actionListener.LoginActionListener;
import com.example.demo.builder.builder.LoginWindowBuilder;

import java.awt.*;

public class LoginWindowLoginBuilder extends LoginWindowBuilder {
    @Override
    public void buildBackButton() {super.buildBackButtonBuilder(0);}
    @Override
    public void buildFunctionButton() {
        loginWindow.setStudentBTN(new Button("학생으로 Login"));
        loginWindow.getStudentBTN().setBounds(20, 5, 70, 30);
        loginWindow.getStudentBTN().addActionListener(new LoginActionListener(loginWindow, 0));
        loginWindow.add(loginWindow.getStudentBTN());

        loginWindow.setAdminBTN(new Button("관리자로 Login"));
        loginWindow.getAdminBTN().setBounds(20, 5, 70, 30);
        loginWindow.getAdminBTN().addActionListener(new LoginActionListener(loginWindow, 0));
        loginWindow.add(loginWindow.getAdminBTN());
    }

}
