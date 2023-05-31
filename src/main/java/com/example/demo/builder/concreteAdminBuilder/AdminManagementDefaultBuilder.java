package com.example.demo.builder.concreteAdminBuilder;

import com.example.demo.builder.builder.AdminManagementWindowBuilder;
import com.example.demo.domain.Book;

import javax.swing.*;

public class AdminManagementDefaultBuilder extends AdminManagementWindowBuilder {

    @Override
    public void buildDetailInfo() {
        JPanel inputPanel = adminManagementWindow.getInputPanel();

        buildLabel(inputPanel, "Title : ");
        createEmptyLabel(inputPanel);

        buildLabel(inputPanel, "ISBN : ");
        createEmptyLabel(inputPanel);

        buildLabel(inputPanel, "Position : ");
        createEmptyLabel(inputPanel);

        buildLabel(inputPanel, "Publisher : ");
        createEmptyLabel(inputPanel);
    }

    private void createEmptyLabel(JPanel inputPanel){
        inputPanel.add(new JLabel(" "));
    }
}
