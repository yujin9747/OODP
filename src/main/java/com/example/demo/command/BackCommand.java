package com.example.demo.command;

import com.example.demo.builder.builder.AdminManagementWindowBuilder;
import com.example.demo.builder.concreteAdminBuilder.AdminManagementDefaultBuilder;
import com.example.demo.builder.concreteMainBuilder.MainWindowAdminBuilder;
import com.example.demo.builder.concreteMainBuilder.MainWindowNullBuilder;
import com.example.demo.builder.concreteMainBuilder.MainWindowUserBuilder;
import com.example.demo.builder.director.AdminManagementWindowDirector;
import com.example.demo.builder.director.MainWindowDirector;
import com.example.demo.domain.Book;
import com.example.demo.domain.Member;
import com.example.demo.domain.Role;
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
        if (beforePage != null && beforePage == 0) {
            MainWindowDirector director;
            if(loginedMember == null){
                MainWindowNullBuilder builder = new MainWindowNullBuilder();
                director = new MainWindowDirector(builder, null);
            }
            else if(loginedMember.getRole() == Role.ADMIN){
                MainWindowAdminBuilder builder = new MainWindowAdminBuilder();
                director = new MainWindowDirector(builder, loginedMember);
            }
            else {
                MainWindowUserBuilder builder = new MainWindowUserBuilder();
                director = new MainWindowDirector(builder, loginedMember);
            }
            director.constructMainWindow();
//            new MainWindow(loginedMember);
        }
        else if(beforePage == null) {
            MainWindowAdminBuilder builder = new MainWindowAdminBuilder();
            MainWindowDirector director = new MainWindowDirector(builder, loginedMember);
            director.constructMainWindow();

        }
        else{
            AdminManagementWindowBuilder builder = new AdminManagementDefaultBuilder();
            AdminManagementWindowDirector director = new AdminManagementWindowDirector(builder, loginedMember, null, null);
            director.constructAdminManagementWindow();
        }

        window.setVisible(false);
    }
}
