package com.example.demo.builder.director;

import com.example.demo.builder.builder.AdminManagementWindowBuilder;
import com.example.demo.builder.builder.SearchWindowBuilder;
import com.example.demo.domain.Book;
import com.example.demo.domain.Member;
import com.example.demo.jframe.AdminManagement;
import com.example.demo.jframe.SearchWindow;

public class AdminManagementWindowDirector {

    private final AdminManagementWindowBuilder adminManagementWindowBuilder;

    private final Member loginedMember;
    private final Book selectedBook;
    private final Integer selectedIdx;

    public AdminManagementWindowDirector(AdminManagementWindowBuilder adminManagementWindowBuilder, Member loginedMember, Book selectedBook, Integer selectedIdx) {
        this.adminManagementWindowBuilder = adminManagementWindowBuilder;
        this.loginedMember = loginedMember;
        this.selectedBook = selectedBook;
        this.selectedIdx = selectedIdx;
    }

    public AdminManagement getAdminManagementWindow(){
        return adminManagementWindowBuilder.getAdminManagementWindow();
    }

    public void constructAdminManagementWindow(){
        adminManagementWindowBuilder.createNewSearchWindowProduct();
        adminManagementWindowBuilder.buildDependencyInjection();
        adminManagementWindowBuilder.buildLoginedMember(loginedMember);
        adminManagementWindowBuilder.buildSelectedBook(selectedBook);
        adminManagementWindowBuilder.buildSelectedIdx(selectedIdx);
        adminManagementWindowBuilder.buildJList();
        adminManagementWindowBuilder.buildInputField();
        adminManagementWindowBuilder.buildButton();
        adminManagementWindowBuilder.buildListener();
        adminManagementWindowBuilder.buildLayout();
        adminManagementWindowBuilder.buildDetailPanel();
        adminManagementWindowBuilder.buildDetailInfo();
        adminManagementWindowBuilder.buildFinished();
        adminManagementWindowBuilder.fillJList();
    }
}
