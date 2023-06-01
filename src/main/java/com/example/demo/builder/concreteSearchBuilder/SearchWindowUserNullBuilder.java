package com.example.demo.builder.concreteSearchBuilder;

import com.example.demo.builder.builder.SearchWindowBuilder;

public class SearchWindowUserNullBuilder extends SearchWindowBuilder {

    @Override
    public void buildBackButton() {
        super.buildBackButtonBuilder(0);
    }

    @Override
    public void buildFunctionButton() {}

    @Override
    public void buildBookInfoLabel() {
        super.buildBookInfoLabelBuilder();
    }
}
