package com.example.demo.builder.builder;

import com.example.demo.domain.Member;
import com.example.demo.jframe.MainWindow;

public abstract class MainWindowBuilder {

    protected MainWindow mainWindow;
    protected Member loginedMember;

    public MainWindow getMainWindow(){
        return mainWindow;
    }
    public MainWindow createNewMainWindow(){
        mainWindow = new MainWindow(loginedMember);
        return mainWindow;
    }



}
