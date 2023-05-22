package com.example.demo.service;

import com.example.demo.domain.Book;
import com.example.demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
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
    @Transactional
//    public void deleteBook(Book book){
//        bookRepository.delete(book);
//    }
    public void deleteBook(String name){
        bookRepository.delete(bookRepository.findByTitle(name));
    }

    public List<Book> findBooks(){
        return bookRepository.findAll();
    }

    public Optional<Book> findOne(Long id){
        return bookRepository.findOne(id);
    }
    public Optional<Book> findBookByTitle(String title){
        return Optional.ofNullable(bookRepository.findByTitle(title));
    }

}
