package com.example.demo.repository;

import com.example.demo.domain.Library;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class LibraryRepository {
    private final List<Library> libraryList = new ArrayList<>();
}
