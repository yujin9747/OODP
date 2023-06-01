package com.example.demo.builder.builder;

import com.example.demo.BeanUtil;
import com.example.demo.actionListener.AdminSearchActionListener;
import com.example.demo.actionListener.SearchActionListener;
import com.example.demo.command.BackCommand;
import com.example.demo.command.ButtonWithCommand;
import com.example.demo.command.Command;
import com.example.demo.command.InitCommand;
import com.example.demo.domain.Book;
import com.example.demo.domain.Member;
import com.example.demo.domain.RentalInfo;
import com.example.demo.jframe.SearchWindow;
import com.example.demo.service.BookService;
import com.example.demo.service.MemberService;
import com.example.demo.service.RentalInfoService;
import com.example.demo.service.ReservationInfoService;

import javax.swing.*;

import java.awt.*;
import java.util.List;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public abstract class SearchWindowBuilder {

    protected SearchWindow searchWindow;
    private RentalInfoService rentalInfoService;
    public SearchWindow getSearchWindow() {
        return searchWindow;
    }

    public void createNewSearchWindowProduct(){
        searchWindow = new SearchWindow();
    }

    public void buildDependencyInjection() {
        searchWindow.setMemberService(BeanUtil.get(MemberService.class));
        searchWindow.setBookService(BeanUtil.get(BookService.class));
        searchWindow.setRentalInfoService(BeanUtil.get(RentalInfoService.class));
        searchWindow.setReservationInfoService(BeanUtil.get(ReservationInfoService.class));
        searchWindow.setRentalInfoService(BeanUtil.get(RentalInfoService.class));
    }

    public void buildLoginedMember(Member loginedMember){
        searchWindow.setLoginedMember(loginedMember);
    }

    public void buildSearchedBook(Book searchedBook){
        searchWindow.setSearchedBook(searchedBook);
    }

    public void buildBeforePage(Integer beforePage){
        searchWindow.setBeforePage(beforePage);
    }

    public void buildWindowTitle(){
        searchWindow.setTitle("Search 결과창");
    }

    public void buildWindowDefaultSetting(){
        searchWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
        searchWindow.setLayout(null);
    }

    public void buildContainer(){
        Container c = searchWindow.getContentPane();
        c.setLayout(new GridLayout(9, 2));
    }

    public void buildJLabel(){
        searchWindow.setTitleLabel(new JLabel("Title : "));
        searchWindow.setPosition(new JLabel("Position : "));
        searchWindow.setStatus(new JLabel("Status :"));
        searchWindow.setIsbn(new JLabel("ISBN : "));
        searchWindow.setPublisher(new JLabel("Publisdher : "));
    }

    public void buildFinished() {
        searchWindow.setSize(600, 600); //창 사이즈
        searchWindow.setVisible(true); //보이기
    }

    public void buildStatusInfo(){
        Book searchedBook = searchWindow.getSearchedBook();
        List<RentalInfo> rentalInfos = searchWindow.getRentalInfoService().findRentalInfosByBookId(searchedBook.getId());

        boolean isBorrowed = false;
        for(int i=0; i<rentalInfos.size(); i++)
            if(rentalInfos.get(i).isReturned() == false) {
                isBorrowed = true;
                break;
            }

        if (isBorrowed) {
            searchWindow.setStatusInfo(new JLabel("대출중"));
        } else if((searchedBook.isReserved())) {
            searchWindow.setStatusInfo(new JLabel("예약중"));
        }
        else {
            searchWindow.setStatusInfo(new JLabel("이용가능"));
        }
    }

    public void buildBookInfoLabelBuilder(){
        Book searchedBook = searchWindow.getSearchedBook();
        searchWindow.setTitleInfo(new JLabel(searchedBook.getTitle()));
        searchWindow.setPositionInfo(new JLabel(searchedBook.getPosition()));
        buildStatusInfo();
        searchWindow.setIsbnInfo(new JLabel(String.valueOf(searchedBook.getIsbn())));
        searchWindow.setPublisherInfo(new JLabel(searchedBook.getPublisher()));

        addBookInfoLabel();
    }

    public void buildBookInfoInputLabelBuilder() {
        Book searchedBook = searchWindow.getSearchedBook();
        searchWindow.setTitleInput(new JTextField(searchedBook.getTitle()));
        searchWindow.setPositionInput(new JTextField(searchedBook.getPosition()));
        buildStatusInfo();
        searchWindow.setIsbnInput(new JTextField(searchedBook.getIsbn().toString()));
        searchWindow.setPublisherInput(new JTextField(searchedBook.getPublisher()));

        addBookInfoInputField();
    }

    private void addBookInfoLabel(){
        searchWindow.add(searchWindow.getTitleLabel());
        searchWindow.add(searchWindow.getTitleInfo());
        searchWindow.add(searchWindow.getPosition());
        searchWindow.add(searchWindow.getPositionInfo());
        searchWindow.add(searchWindow.getStatus());
        searchWindow.add(searchWindow.getStatusInfo());
        searchWindow.add(searchWindow.getIsbn());
        searchWindow.add(searchWindow.getIsbnInfo());
        searchWindow.add(searchWindow.getPublisher());
        searchWindow.add(searchWindow.getPublisherInfo());
    }

    private void addBookInfoInputField(){
        searchWindow.add(searchWindow.getTitleLabel());
        searchWindow.add(searchWindow.getTitleInput());
        searchWindow.add(searchWindow.getPosition());
        searchWindow.add(searchWindow.getPositionInput());
        searchWindow.add(searchWindow.getStatus());
        searchWindow.add(searchWindow.getStatusInfo());
        searchWindow.add(searchWindow.getIsbn());
        searchWindow.add(searchWindow.getIsbnInput());
        searchWindow.add(searchWindow.getPublisher());
        searchWindow.add(searchWindow.getPublisherInput());
    }

    public void buildBackButtonBuilder(){
        ButtonWithCommand buttonWithCommand = new ButtonWithCommand(new InitCommand());
        Command backCommand = new BackCommand(searchWindow.getBeforePage(), searchWindow.getLoginedMember(), searchWindow);
        buttonWithCommand.setCommand(backCommand);
        searchWindow.setBackBTN(new Button("<"));
        searchWindow.getBackBTN().addActionListener(new AdminSearchActionListener(buttonWithCommand));
        searchWindow.add(searchWindow.getBackBTN());
        searchWindow.add(new JLabel(" "));
        searchWindow.setVisible(false);
    }
    public abstract void buildBackButton();
    public abstract void buildFunctionButton();
    public abstract void buildBookInfoLabel();

}
