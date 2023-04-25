package com.example.demo.repository;

import com.example.demo.domain.Library;
import com.example.demo.domain.Member;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
public class LibraryRepository {
    private final EntityManager em;

    public void save(Library library) {
        em.persist(library);
    }
}
