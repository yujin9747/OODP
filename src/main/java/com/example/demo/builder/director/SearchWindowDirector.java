package com.example.demo.builder.director;

import com.example.demo.builder.builder.SearchWindowBuilder;
import com.example.demo.jframe.SearchWindow;

public class SearchWindowDirector {

    private SearchWindowBuilder searchWindowBuilder;

    public void setSearchWindowBuilder(SearchWindowBuilder builder){
        searchWindowBuilder = builder;
    }

    public SearchWindow getSearchWindow(){
        return searchWindowBuilder.getSearchWindow();
    }

    public void constructSearchWindow(){
        searchWindowBuilder.createNewSearchWindowProduct();
    }
}
