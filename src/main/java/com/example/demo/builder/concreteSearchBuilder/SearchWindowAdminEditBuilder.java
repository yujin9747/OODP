package com.example.demo.builder.concreteSearchBuilder;

import com.example.demo.actionListener.SearchActionListener;
import com.example.demo.builder.builder.SearchWindowBuilder;

import java.awt.*;

public class SearchWindowAdminEditBuilder extends SearchWindowBuilder {

    @Override
    public void buildBackButton() {}

    @Override
    public void buildFunctionButton() {
        searchWindow.setEditBTN(new Button("수정완료"));
        searchWindow.getEditBTN().setBounds(20, 5, 70, 30);
        searchWindow.getEditBTN().addActionListener(new SearchActionListener());
        searchWindow.add(searchWindow.getEditBTN());
    }

    @Override
    public void buildBookInfoLabel() {
        super.buildBookInfoInputLabelBuilder();
    }
}
