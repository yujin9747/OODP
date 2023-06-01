package com.example.demo.actionListener;

import com.example.demo.builder.builder.SearchWindowBuilder;
import com.example.demo.builder.concreteSearchBuilder.SearchWindowAdminBuilder;
import com.example.demo.builder.concreteSearchBuilder.SearchWindowUserBuilder;
import com.example.demo.builder.concreteSearchBuilder.SearchWindowUserNullBuilder;
import com.example.demo.builder.director.SearchWindowDirector;
import com.example.demo.command.ButtonWithCommand;
import com.example.demo.domain.Book;
import com.example.demo.domain.Member;
import com.example.demo.domain.Role;
import com.example.demo.domain.request.BookUpdateForm;
import com.example.demo.jframe.AdminManagement;
import com.example.demo.jframe.MainWindow;
import com.example.demo.jframe.SearchWindow;
import com.example.demo.service.BookService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class AdminSearchActionListener implements ActionListener {

    private SearchWindow searchWindow;

    public AdminSearchActionListener(SearchWindow searchWindow) {
        this.searchWindow = searchWindow;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        SearchWindowBuilder searchWindowBuilder = new SearchWindowAdminBuilder();

        if(command.equals("<")){
            if (searchWindow.getBeforePage() != 1)
                new MainWindow(searchWindow.getLoginedMember());
            else
                new AdminManagement(searchWindow.getLoginedMember(),  null, null);
//            setVisible(false);
        } else if (command.equals("수정하기")) {
            SearchWindowDirector searchWindowDirector = new SearchWindowDirector(searchWindowBuilder, searchWindow.getLoginedMember(), searchWindow.getSearchedBook(),  searchWindow.getBeforePage());
            searchWindowDirector.constructSearchWindow();
//            setVisible(false);
        } else if (command.equals("삭제하기")) {
            searchWindow.getBookService().deleteBook(searchWindow.getSearchedBook().getTitle());
//            setVisible(false);
            new AdminManagement(searchWindow.getLoginedMember(),  null, null);
            JOptionPane.showMessageDialog(null, searchWindow.getSearchedBook().getTitle() + "책 삭제가 완료되었습니다.");
        } else if (command.equals("수정완료")) {
            BookUpdateForm bookUpdateForm = new BookUpdateForm.BookUpdateFormBuilder()
                    .title(searchWindow.getTitleInput().getText())
                    .isbn(searchWindow.getIsbnInput().getText())
                    .position(searchWindow.getPositionInput().getText())
                    .publisher(searchWindow.getPublisherInput().getText())
                    .build();
            Optional<Book> book = searchWindow.getBookService().updateBook(searchWindow.getSearchedBook().getTitle(), bookUpdateForm);
            SearchWindowDirector searchWindowDirector = new SearchWindowDirector(searchWindowBuilder, searchWindow.getLoginedMember(), book.get(), searchWindow.getBeforePage());
            searchWindowDirector.constructSearchWindow();
//            setVisible(false);
        }
    }
}
