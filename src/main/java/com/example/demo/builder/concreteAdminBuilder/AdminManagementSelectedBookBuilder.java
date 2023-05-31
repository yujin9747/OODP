package com.example.demo.builder.concreteAdminBuilder;

import com.example.demo.builder.builder.AdminManagementWindowBuilder;
import com.example.demo.domain.Book;

import javax.swing.*;

public class AdminManagementSelectedBookBuilder extends AdminManagementWindowBuilder {
    @Override
    public void buildDetailInfo() {
        JPanel inputPanel = adminManagementWindow.getInputPanel();
        Book selectedBook = adminManagementWindow.getSelectedBook();

        buildLabel(inputPanel, "Title : ");
        createDetailLabel(inputPanel, selectedBook.getTitle());

        buildLabel(inputPanel, "ISBN : ");
        createDetailLabel(inputPanel, selectedBook.getIsbn().toString());

        buildLabel(inputPanel, "Position : ");
        createDetailLabel(inputPanel, selectedBook.getPosition());

        buildLabel(inputPanel, "Publisher : ");
        createDetailLabel(inputPanel, selectedBook.getPublisher());
    }

    private void createDetailLabel(JPanel inputPanel, String detailInfo){
        inputPanel.add(new JLabel(detailInfo));
    }
}
