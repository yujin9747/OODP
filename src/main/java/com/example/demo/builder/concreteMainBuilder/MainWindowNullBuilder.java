package com.example.demo.builder.concreteMainBuilder;

import com.example.demo.builder.builder.MainWindowBuilder;
import com.example.demo.jframe.MainWindow;

import java.awt.*;

public class MainWindowNullBuilder extends MainWindowBuilder {
    @Override
    public void builWindowButton() {
        mainWindow.setLoginBTN(new Button("Login"));
        mainWindow.getLoginBTN().setBounds(300, 300, 70, 30);
        mainWindow.add(mainWindow.getLoginBTN());
        mainWindow.getLoginBTN().addActionListener(new LoginActionListener());
        mainWindow.setRegisterBTN(new Button("Register"));
        mainWindow.getRegisterBTN().setBounds(300, 300, 70, 30);
        mainWindow.add(mainWindow.getRegisterBTN());
        mainWindow.getRegisterBTN().addActionListener(new RegisterActionListener());
    }

}
