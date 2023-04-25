package com.example.demo.service;

import com.example.demo.domain.Library;
import com.example.demo.domain.Member;
import com.example.demo.repository.LibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LibraryService {

    private final LibraryRepository libraryRepository;

    @Transactional
    public void saveLibrary(Library library){
        libraryRepository.save(library);
    }
}
