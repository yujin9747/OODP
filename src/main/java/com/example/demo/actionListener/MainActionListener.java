package com.example.demo.actionListener;

import com.example.demo.BeanUtil;
import com.example.demo.builder.builder.SearchWindowBuilder;
import com.example.demo.builder.concreteSearchBuilder.SearchWindowAdminBuilder;
import com.example.demo.builder.concreteSearchBuilder.SearchWindowUserBuilder;
import com.example.demo.builder.concreteSearchBuilder.SearchWindowUserNullBuilder;
import com.example.demo.builder.director.SearchWindowDirector;
import com.example.demo.domain.Book;
import com.example.demo.domain.Member;
import com.example.demo.domain.Role;
import com.example.demo.jframe.MainWindow;
import com.example.demo.service.BookService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class MainActionListener implements ActionListener {
    private MainWindow mainWindow;
    public MainActionListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String bookTitle = mainWindow.getSearchBoxField().getText();
        Member loginedMember = mainWindow.getLoginedMember();
        BookService bookService = BeanUtil.get(BookService.class);

        Optional<Book> searchedBook = bookService.findBookByTitle(bookTitle);
        if(searchedBook.isPresent() == false) {
            JOptionPane.showMessageDialog(null, "일치하는 책 정보가 없습니다.");
        }
        else{
            SearchWindowBuilder searchWindowBuilder = new SearchWindowUserNullBuilder();
            if(loginedMember != null){
                if(loginedMember.getRole() == Role.ADMIN) searchWindowBuilder = new SearchWindowAdminBuilder();
                else if(loginedMember.getRole() == Role.STUDENT) searchWindowBuilder = new SearchWindowUserBuilder();
            }
            SearchWindowDirector searchWindowDirector = new SearchWindowDirector(searchWindowBuilder, loginedMember, searchedBook.get(), 0);
            searchWindowDirector.constructSearchWindow();
            mainWindow.setVisible(false);
        }
    }
}
