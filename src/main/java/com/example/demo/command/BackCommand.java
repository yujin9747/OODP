package com.example.demo.command;

import com.example.demo.builder.builder.AdminManagementWindowBuilder;
import com.example.demo.builder.concreteAdminBuilder.AdminManagementDefaultBuilder;
import com.example.demo.builder.director.AdminManagementWindowDirector;
import com.example.demo.domain.Book;
import com.example.demo.domain.Member;
import com.example.demo.jframe.MainWindow;
import com.example.demo.jframe.SearchWindow;
import com.example.demo.service.ReservationInfoService;

import javax.swing.*;

public class BackCommand implements Command{
    private Integer beforePage;
    private Member loginedMember;
    private JFrame window;

    public BackCommand(Integer beforePage, Member loginedMember, JFrame window) {
        this.beforePage = beforePage;
        this.loginedMember = loginedMember;
        this.window = window;
    }

    public void execute() {
        if (beforePage == 0) {
            new MainWindow(loginedMember);
        }
        else{
            AdminManagementWindowBuilder builder = new AdminManagementDefaultBuilder();
            AdminManagementWindowDirector director = new AdminManagementWindowDirector(builder, loginedMember, null, null);
            director.constructAdminManagementWindow();
        }

        window.setVisible(false);
    }
}
