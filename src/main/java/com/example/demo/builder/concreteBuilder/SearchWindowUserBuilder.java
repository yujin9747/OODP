package com.example.demo.builder.concreteBuilder;

import com.example.demo.actionListener.SearchActionListener;
import com.example.demo.builder.builder.SearchWindowBuilder;
import com.example.demo.domain.Book;

import javax.swing.*;
import java.awt.*;

public class SearchWindowUserBuilder extends SearchWindowBuilder {

    @Override
    public void buildBackButton() {
        searchWindow.setBackBTN(new Button("<"));
        searchWindow.getBackBTN().addActionListener(new SearchActionListener());
        searchWindow.add(searchWindow.getBackBTN());
        searchWindow.add(new JLabel(" "));
    }

    @Override
    public void buildFunctionButton() {

    }

    @Override
    public void buildBookInfoLabel() {
        super.buildBookInfoLabelBuilder();
    }
}
