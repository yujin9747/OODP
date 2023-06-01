package com.example.demo.command;

import com.example.demo.BeanUtil;
import com.example.demo.builder.concreteAdminBuilder.AdminManagementDefaultBuilder;
import com.example.demo.builder.concreteSearchBuilder.SearchWindowAdminBuilder;
import com.example.demo.builder.director.AdminManagementWindowDirector;
import com.example.demo.builder.director.SearchWindowDirector;
import com.example.demo.domain.Book;
import com.example.demo.domain.Member;
import com.example.demo.domain.request.BookUpdateForm;
import com.example.demo.jframe.SearchWindow;
import com.example.demo.service.BookService;

import javax.swing.*;
import java.util.Optional;

public class EditFinishCommand implements Command{
    private Integer beforePage;
    private Member loginedMember;
    private SearchWindow searchWindow;
    private Book searchedBook;

    public EditFinishCommand(Integer beforePage, Member loginedMember, SearchWindow searchWindow, Book searchedBook) {
        this.beforePage = beforePage;
        this.loginedMember = loginedMember;
        this.searchWindow = searchWindow;
        this.searchedBook = searchedBook;
    }

    public void execute() {
        BookUpdateForm bookUpdateForm = new BookUpdateForm.BookUpdateFormBuilder()
                        .title(searchWindow.getTitleInput().getText())
                        .isbn(searchWindow.getIsbnInput().getText())
                        .position(searchWindow.getPositionInput().getText())
                        .publisher(searchWindow.getPublisherInput().getText())
                        .build();

        Optional<Book> book = BeanUtil.get(BookService.class).updateBook(searchedBook.getTitle(), bookUpdateForm);

        SearchWindowAdminBuilder builder = new SearchWindowAdminBuilder();
        SearchWindowDirector director = new SearchWindowDirector(builder, loginedMember, book.get(), beforePage);
        searchWindow.setVisible(false);
        director.constructSearchWindow();
        searchWindow.setVisible(false);
    }
}
