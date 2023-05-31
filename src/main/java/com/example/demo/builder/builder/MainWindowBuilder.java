package com.example.demo.builder.builder;

import com.example.demo.BeanUtil;
import com.example.demo.domain.Member;
import com.example.demo.jframe.MainWindow;
import com.example.demo.jframe.SearchWindow;
import com.example.demo.service.BookService;
import com.example.demo.service.MemberService;
import com.example.demo.service.RentalInfoService;
import com.example.demo.service.ReservationInfoService;

public abstract class MainWindowBuilder {

    protected MainWindow mainWindow;
    private Member loginedMember;
    public void createNewMainWindowProduct(){
        mainWindow = new MainWindow(loginedMember);
    }

    public MainWindow getMainWindow(){

        return mainWindow;
    }
    public void buildLoginedMember(Member loginedMember){
        mainWindow.setLoginedMember(loginedMember);
    }

}
