package com.example.demo.builder.concreteSearchBuilder;

import com.example.demo.actionListener.SearchActionListener;
import com.example.demo.builder.builder.SearchWindowBuilder;


import java.awt.*;

public class SearchWindowAdminBuilder extends SearchWindowBuilder {

    @Override
    public void buildBackButton() {
        super.buildBackButtonBuilder();
    }

    @Override
    public void buildFunctionButton() {
        searchWindow.setEditBTN(new Button("수정하기"));
        searchWindow.getEditBTN().setBounds(20, 5, 70, 30);
        searchWindow.getEditBTN().addActionListener(new SearchActionListener());

        searchWindow.setDeleteBTN(new Button("삭제하기"));
        searchWindow.getDeleteBTN().setBounds(20, 5, 70, 30);
        searchWindow.getDeleteBTN().addActionListener(new SearchActionListener());

        searchWindow.add(searchWindow.getEditBTN());
        searchWindow.add(searchWindow.getDeleteBTN());
    }

    @Override
    public void buildBookInfoLabel() {
        super.buildBookInfoLabelBuilder();
    }
}
