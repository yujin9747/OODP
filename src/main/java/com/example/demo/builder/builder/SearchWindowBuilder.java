package com.example.demo.builder.builder;

import com.example.demo.BeanUtil;
import com.example.demo.domain.Book;
import com.example.demo.domain.Member;
import com.example.demo.jframe.SearchWindow;
import com.example.demo.service.BookService;
import com.example.demo.service.MemberService;
import com.example.demo.service.RentalInfoService;
import com.example.demo.service.ReservationInfoService;

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

//    public void buildWindowTitle(String title){
//        searchWindow.
//    }
}
