package com.example.demo.service;

import com.example.demo.domain.Book;
import com.example.demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public void saveBook(Book book){
        bookRepository.save(book);
    }

    public List<Book> findBooks(){
        return bookRepository.findAll();
    }

    public Optional<Book> findOne(Long id){
        return bookRepository.findOne(id);
    }
}
