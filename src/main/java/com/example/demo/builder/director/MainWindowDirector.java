package com.example.demo.builder.director;

import com.example.demo.builder.builder.MainWindowBuilder;
import com.example.demo.jframe.MainWindow;

public class MainWindowDirector {

    private MainWindowBuilder mainWindowBuilder;

    public void setMainWindowDirector(MainWindowBuilder builder){
        mainWindowBuilder = builder;
    }

    public MainWindow getMainWindow(){
        return mainWindowBuilder.getMainWindow();
    }

    public void constructMainWindow(){
        mainWindowBuilder.createNewMainWindowProduct();
    }

}
