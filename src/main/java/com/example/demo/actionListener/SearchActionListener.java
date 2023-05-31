package com.example.demo.actionListener;

import com.example.demo.command.ButtonWithCommand;
import com.example.demo.domain.Book;
import com.example.demo.domain.Role;
import com.example.demo.domain.request.BookUpdateForm;
import com.example.demo.jframe.AdminManagement;
import com.example.demo.jframe.MainWindow;
import com.example.demo.jframe.SearchWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class SearchActionListener implements ActionListener {

    private ButtonWithCommand buttonWithCommand;

    public SearchActionListener(ButtonWithCommand buttonWithCommand) {
        this.buttonWithCommand = buttonWithCommand;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        buttonWithCommand.pressed();
    }

}