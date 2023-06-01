package com.example.demo.builder.concreteBuilder;

import com.example.demo.builder.builder.MainWindowBuilder;
import com.example.demo.domain.Role;
import com.example.demo.jframe.MainWindow;

import java.awt.*;

public class MainWindowUserBuilder extends MainWindowBuilder{
    @Override
    public void builWindowButton() {
        mainWindow.setLogoutBTN(new Button("Logout"));
        mainWindow.getLogoutBTN().setBounds(300, 300, 70, 30);
        mainWindow.getLogoutBTN().addActionListener(new LogoutActionListener());
        mainWindow.setUserPageBTN(new Button("User Page"));
        mainWindow.getUserPageBTN().setBounds(300, 350, 70, 30);
        mainWindow.add(mainWindow.getUserPageBTN());
        mainWindow.getUserPageBTN().addActionListener(new UserPageActionListener());
    }
}
