package com.example.demo.builder.director;

import com.example.demo.BeanUtil;
import com.example.demo.builder.builder.SearchWindowBuilder;
import com.example.demo.domain.Book;
import com.example.demo.domain.Member;
import com.example.demo.jframe.SearchWindow;
import com.example.demo.service.RentalInfoService;

public class SearchWindowDirector {

    private final SearchWindowBuilder searchWindowBuilder;

    private final Member loginedMember;
    private final Book searchedBook;
    private final Integer beforePage;

    public SearchWindowDirector(SearchWindowBuilder searchWindowBuilder, Member loginedMember, Book searchedBook, Integer beforePage) {
        this.searchWindowBuilder = searchWindowBuilder;
        this.loginedMember = loginedMember;
        this.searchedBook = searchedBook;
        this.beforePage = beforePage;
    }

    public SearchWindow getSearchWindow(){
        return searchWindowBuilder.getSearchWindow();
    }

    public void constructSearchWindow(){
        searchWindowBuilder.createNewSearchWindowProduct();
        searchWindowBuilder.buildDependencyInjection();
        searchWindowBuilder.buildLoginedMember(loginedMember);
        searchWindowBuilder.buildSearchedBook(searchedBook);
        searchWindowBuilder.buildBeforePage(beforePage);
        searchWindowBuilder.buildWindowTitle();
        searchWindowBuilder.buildWindowDefaultSetting();
        searchWindowBuilder.buildContainer();
        searchWindowBuilder.buildJLabel();
        searchWindowBuilder.buildBackButton();
        searchWindowBuilder.buildBookInfoLabel();
        searchWindowBuilder.buildFunctionButton();
        searchWindowBuilder.buildFinished();
    }
}
