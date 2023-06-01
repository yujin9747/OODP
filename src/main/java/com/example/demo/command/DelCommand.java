package com.example.demo.command;

import com.example.demo.builder.concreteAdminBuilder.AdminManagementDefaultBuilder;
import com.example.demo.builder.concreteSearchBuilder.SearchWindowAdminEditBuilder;
import com.example.demo.builder.director.AdminManagementWindowDirector;
import com.example.demo.builder.director.SearchWindowDirector;
import com.example.demo.domain.Book;
import com.example.demo.domain.Member;
import com.example.demo.jframe.SearchWindow;
import com.example.demo.service.BookService;

import javax.swing.*;

public class DelCommand implements Command{
    private Integer beforePage;
    private Member loginedMember;
    private SearchWindow searchWindow;
    private Book searchedBook;
    private BookService bookService;

    public DelCommand(Integer beforePage, Member loginedMember, SearchWindow searchWindow, Book searchedBook, BookService bookService) {
        this.beforePage = beforePage;
        this.loginedMember = loginedMember;
        this.searchWindow = searchWindow;
        this.searchedBook = searchedBook;
        this.bookService = bookService;
    }

    public void execute() {
        bookService.deleteBook(searchedBook.getTitle());
        AdminManagementDefaultBuilder builder = new AdminManagementDefaultBuilder();
        AdminManagementWindowDirector director = new AdminManagementWindowDirector(builder, loginedMember, searchedBook, beforePage);
        director.constructAdminManagementWindow();
        searchWindow.setVisible(false);
        JOptionPane.showMessageDialog(null, searchedBook.getTitle() + "책 삭제가 완료되었습니다.");
    }
}
