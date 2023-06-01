package com.example.demo.builder.concreteSearchBuilder;

import com.example.demo.actionListener.AdminSearchActionListener;
import com.example.demo.actionListener.SearchActionListener;
import com.example.demo.builder.builder.SearchWindowBuilder;
import com.example.demo.command.*;

import java.awt.*;

public class SearchWindowAdminEditBuilder extends SearchWindowBuilder {

    @Override
    public void buildBackButton() {}

    @Override
    public void buildFunctionButton() {
        ButtonWithCommand buttonWithCommand = new ButtonWithCommand(new InitCommand());
        Command editFinishCommand = new EditFinishCommand(searchWindow.getBeforePage(), searchWindow.getLoginedMember(), searchWindow, searchWindow.getSearchedBook());
        buttonWithCommand.setCommand(editFinishCommand);
        searchWindow.setEditBTN(new Button("수정완료"));
        searchWindow.getEditBTN().setBounds(20, 5, 70, 30);
        searchWindow.getEditBTN().addActionListener(new AdminSearchActionListener(buttonWithCommand));
        searchWindow.add(searchWindow.getEditBTN());
    }

    @Override
    public void buildBookInfoLabel() {
        super.buildBookInfoInputLabelBuilder();
    }
}
