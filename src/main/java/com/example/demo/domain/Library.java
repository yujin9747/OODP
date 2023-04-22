package com.example.demo.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Library {
    private Long libraryId;
    private String name;
    private int totalBook;

    public Library(Long libraryId, String name, int totalBook){
        this.libraryId = libraryId;
        this.name = name;
        this.totalBook = totalBook;
    }
}
