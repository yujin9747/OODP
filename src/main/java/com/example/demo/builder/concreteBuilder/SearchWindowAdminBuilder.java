package com.example.demo.builder.concreteBuilder;

import com.example.demo.actionListener.SearchActionListener;
import com.example.demo.builder.builder.SearchWindowBuilder;
import com.example.demo.domain.Book;
import com.example.demo.jframe.SearchWindow;


import javax.swing.*;
import java.awt.*;

public class SearchWindowAdminBuilder extends SearchWindowBuilder {

    @Override
    public void buildBackButton() {
        searchWindow.setBackBTN(new Button("<"));
        searchWindow.getBackBTN().addActionListener(new SearchActionListener());
        searchWindow.add(searchWindow.getBackBTN());
        searchWindow.add(new JLabel(" "));
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
        Book searchedBook = searchWindow.getSearchedBook();
        searchWindow.setTitleInfo(new JLabel(searchedBook.getTitle()));
        searchWindow.setPositionInfo(new JLabel(searchedBook.getPosition()));
        buildStatusInfo();
        searchWindow.setIsbnInfo(new JLabel(String.valueOf(searchedBook.getIsbn())));
        searchWindow.setPublisherInfo(new JLabel(searchedBook.getPublisher()));

        addBookInfoLabel();
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
}
