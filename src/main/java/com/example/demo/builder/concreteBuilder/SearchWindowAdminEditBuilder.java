package com.example.demo.builder.concreteBuilder;

import com.example.demo.actionListener.SearchActionListener;
import com.example.demo.builder.builder.SearchWindowBuilder;
import com.example.demo.domain.Book;

import javax.swing.*;
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
        Book searchedBook = searchWindow.getSearchedBook();
        searchWindow.setTitleInput(new JTextField(searchedBook.getTitle()));
        searchWindow.setPositionInput(new JTextField(searchedBook.getPosition()));
        buildStatusInfo();
        searchWindow.setIsbnInput(new JTextField(searchedBook.getIsbn().toString()));
        searchWindow.setPublisherInput(new JTextField(searchedBook.getPublisher()));

        addBookInfoInputField();
    }

    private void addBookInfoInputField(){
        searchWindow.add(searchWindow.getTitleLabel());
        searchWindow.add(searchWindow.getTitleInput());
        searchWindow.add(searchWindow.getPosition());
        searchWindow.add(searchWindow.getPositionInput());
        searchWindow.add(searchWindow.getStatus());
        searchWindow.add(searchWindow.getStatusInfo());
        searchWindow.add(searchWindow.getIsbn());
        searchWindow.add(searchWindow.getIsbnInput());
        searchWindow.add(searchWindow.getPublisher());
        searchWindow.add(searchWindow.getPublisherInput());
    }
}
