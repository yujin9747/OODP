package com.example.demo.builder.builder;

import com.example.demo.BeanUtil;
import com.example.demo.domain.Member;
import com.example.demo.jframe.MainWindow;
import com.example.demo.jframe.SearchWindow;
import com.example.demo.actionListener.SearchActionListener;
import com.example.demo.service.BookService;
import com.example.demo.service.MemberService;
import com.example.demo.service.RentalInfoService;
import com.example.demo.service.ReservationInfoService;

import javax.swing.*;

import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public abstract class MainWindowBuilder {

    protected MainWindow mainWindow;
    private Member loginedMember;

    public void createNewMainWindowProduct(Member loginedMember){
        mainWindow = new MainWindow(loginedMember);
    }
    public void buildDependencyInjection() {
        mainWindow.setMemberService(BeanUtil.get(MemberService.class));
        mainWindow.setBookService(BeanUtil.get(BookService.class));
    }

    public MainWindow getMainWindow(){
        return mainWindow;
    }
    public void buildLoginedMember(Member loginedMember){
        mainWindow.setLoginedMember(loginedMember);
    }
    public void buildSearchBoxField(){
        mainWindow.setSearchBoxField(new JTextField("책 제목을 입력하세요", 20));
    }
    public void buildWindowTitle(){
        mainWindow.setTitle("Main");
    }
    public void buildWindowSize(){
        mainWindow.setSize(600, 600);
    }
    public void buildWindowDefaultSetting(){
        mainWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainWindow.setLayout(null);
    }
    public void buildContainer(){
        Container c = mainWindow.getContentPane();
        c.setLayout(new FlowLayout());
        c.add(mainWindow.getSearchBoxField());
    }
    public void buildSearchBTN(){
        mainWindow.setSearchBTN(new Button("search"));
        mainWindow.setBounds(20, 5, 70, 30);
        mainWindow.add(mainWindow.getSearchBTN());
        mainWindow.getSearchBTN().addActionListener(new SearchActionListener());
    }
    public void buildVisible(){
        mainWindow.setVisible(true);
    }

    setVisible(true); //보이기



}
