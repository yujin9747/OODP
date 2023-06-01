package com.example.demo.command;

import com.example.demo.builder.builder.AdminManagementWindowBuilder;
import com.example.demo.builder.concreteAdminBuilder.AdminManagementDefaultBuilder;
import com.example.demo.builder.concreteSearchBuilder.SearchWindowAdminEditBuilder;
import com.example.demo.builder.director.AdminManagementWindowDirector;
import com.example.demo.builder.director.SearchWindowDirector;
import com.example.demo.domain.Book;
import com.example.demo.domain.Member;
import com.example.demo.jframe.MainWindow;
import com.example.demo.jframe.SearchWindow;
import com.example.demo.service.RentalInfoService;

public class EditCommand implements Command{
    private Integer beforePage;
    private Member loginedMember;
    private SearchWindow searchWindow;
    private Book searchedBook;

    public EditCommand(Integer beforePage, Member loginedMember, SearchWindow searchWindow, Book searchedBook) {
        this.beforePage = beforePage;
        this.loginedMember = loginedMember;
        this.searchWindow = searchWindow;
        this.searchedBook = searchedBook;
    }

    public void execute() {
        SearchWindowAdminEditBuilder builder = new SearchWindowAdminEditBuilder();
        SearchWindowDirector director = new SearchWindowDirector(builder, loginedMember, searchedBook, beforePage);
        director.constructSearchWindow();
        searchWindow.setVisible(false);
    }
}
