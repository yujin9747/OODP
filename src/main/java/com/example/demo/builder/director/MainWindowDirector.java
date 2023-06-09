package com.example.demo.builder.director;

import com.example.demo.builder.builder.MainWindowBuilder;
import com.example.demo.domain.Member;
import com.example.demo.jframe.MainWindow;

public class MainWindowDirector {

    private final MainWindowBuilder mainWindowBuilder;
    private final Member loginedMember;

    public MainWindowDirector(MainWindowBuilder mainWindowBuilder, Member loginedMember){
        this.mainWindowBuilder = mainWindowBuilder;
        this.loginedMember = loginedMember;

    }

    public MainWindow getMainWindow(){
        return mainWindowBuilder.getMainWindow();
    }

    public void constructMainWindow(){
        mainWindowBuilder.createNewMainWindowProduct();
        mainWindowBuilder.buildLoginedMember(loginedMember);
        mainWindowBuilder.buildSearchBoxField();
        mainWindowBuilder.buildContainer();
        mainWindowBuilder.buildSearchBTN();
        mainWindowBuilder.builWindowButton();
        mainWindowBuilder.buildDefaultSetting();
        mainWindowBuilder.buildVisible();
    }
}
