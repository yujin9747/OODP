package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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

    public Library(Long id, String name, int totalBook){
        this.id = id;
        this.name = name;
        this.totalBook = totalBook;
    }
}
