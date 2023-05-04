package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "library_id", nullable = false)
    private Long id;
    private String name;
    private int totalBook;

    @OneToMany(mappedBy = "library")
    private List<Book> books = new ArrayList<>();

    @OneToMany(mappedBy = "library")
    private List<Member> members = new ArrayList<>();

    public Library(String name){
        this.name = name;
        this.totalBook = 0;
    }
}
