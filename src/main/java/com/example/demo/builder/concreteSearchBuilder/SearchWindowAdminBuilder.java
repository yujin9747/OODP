package com.example.demo.builder.concreteSearchBuilder;

import com.example.demo.actionListener.AdminSearchActionListener;
import com.example.demo.actionListener.SearchActionListener;
import com.example.demo.builder.builder.SearchWindowBuilder;
import com.example.demo.command.*;


import java.awt.*;

public class SearchWindowAdminBuilder extends SearchWindowBuilder {


    @Override
    public void buildBackButton() {
        super.buildBackButtonBuilder(searchWindow.getBeforePage());
    }

    @Override
    public void buildFunctionButton() {
        ButtonWithCommand buttonWithCommand = new ButtonWithCommand(new InitCommand());
        Command editCommand = new EditCommand(searchWindow.getBeforePage(), searchWindow.getLoginedMember(), searchWindow, searchWindow.getSearchedBook());
        buttonWithCommand.setCommand(editCommand);
        searchWindow.setEditBTN(new Button("수정하기"));
        searchWindow.getEditBTN().setBounds(20, 5, 70, 30);
        searchWindow.getEditBTN().addActionListener(new AdminSearchActionListener(buttonWithCommand));

        ButtonWithCommand buttonWithCommand2 = new ButtonWithCommand(new InitCommand());
        Command delCommand = new DelCommand(searchWindow.getBeforePage(), searchWindow.getLoginedMember(), searchWindow, searchWindow.getSearchedBook(), searchWindow.getBookService());
        buttonWithCommand2.setCommand(delCommand);
        searchWindow.setDeleteBTN(new Button("삭제하기"));
        searchWindow.getDeleteBTN().setBounds(20, 5, 70, 30);
        searchWindow.getDeleteBTN().addActionListener(new AdminSearchActionListener(buttonWithCommand2));

        searchWindow.add(searchWindow.getEditBTN());
        searchWindow.add(searchWindow.getDeleteBTN());
    }

    @Override
    public void buildBookInfoLabel() {
        super.buildBookInfoLabelBuilder();
    }
}
