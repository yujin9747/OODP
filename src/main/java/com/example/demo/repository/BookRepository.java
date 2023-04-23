package com.example.demo.repository;

import com.example.demo.domain.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BookRepository {
    private List<Book> bookList = new ArrayList<>();
}
