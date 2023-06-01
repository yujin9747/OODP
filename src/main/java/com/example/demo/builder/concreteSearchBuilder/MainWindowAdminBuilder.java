package com.example.demo.builder.concreteBuilder;

import com.example.demo.builder.builder.MainWindowBuilder;
import com.example.demo.domain.Role;
import com.example.demo.jframe.MainWindow;

import java.awt.*;

public class MainWindowAdminBuilder extends MainWindowBuilder{
    @Override
    public void builWindowButton() {
        mainWindow.setLogoutBTN(new Button("Logout"));
        mainWindow.getLogoutBTN().setBounds(300, 300, 70, 30);
        mainWindow.getLogoutBTN().addActionListener(new LogoutActionListener());
        mainWindow.setAdminPageBTN(new Button("Admin Page"));
        mainWindow.getAdminPageBTN().setBounds(300, 350, 70, 30);
        mainWindow.add(mainWindow.getAdminPageBTN());
        mainWindow.getAdminPageBTN().addActionListener(new AdminPageActionListener());
    }
}
