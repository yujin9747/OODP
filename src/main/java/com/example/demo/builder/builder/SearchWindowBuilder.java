package com.example.demo.builder.builder;

import com.example.demo.BeanUtil;
import com.example.demo.domain.Book;
import com.example.demo.domain.Member;
import com.example.demo.jframe.SearchWindow;
import com.example.demo.service.BookService;
import com.example.demo.service.MemberService;
import com.example.demo.service.RentalInfoService;
import com.example.demo.service.ReservationInfoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public abstract class SearchWindowBuilder {

    protected SearchWindow searchWindow;


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

    public void buildBookInfoLabel(){
        Book searchedBook = searchWindow.getSearchedBook();
        searchWindow.setTitleInfo(new JLabel(searchedBook.getTitle()));
        searchWindow.setPositionInfo(new JLabel(searchedBook.getPosition()));
        if ((searchedBook.isBorrowed())) {
            searchWindow.setStatusInfo(new JLabel("대출중"));
        } else if((searchedBook.isReserved())) {
            searchWindow.setStatusInfo(new JLabel("예약중"));
        }
        else {
            searchWindow.setStatusInfo(new JLabel("이용가능"));
        }
        searchWindow.setIsbnInfo(new JLabel(String.valueOf(searchedBook.getIsbn())));
        searchWindow.setPublisherInfo(new JLabel(searchedBook.getPublisher()));

        addBookInfLabel();
    }

    private void addBookInfLabel(){
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

    public void buildFinished() {
        searchWindow.setSize(600, 600); //창 사이즈
        searchWindow.setVisible(true); //보이기
    }

    public abstract void buildBackButton();
    public abstract void buildFunctionButton();

}
