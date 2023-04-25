package com.example.demo.repository;

import com.example.demo.domain.Book;
import com.example.demo.domain.Member;
import com.example.demo.domain.Professor;
import com.example.demo.domain.Student;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class BookRepository {

    private final EntityManager em;

    public void save(Book book) {
        em.persist(book);
    }

    public Long delete(Book book) {
        em.remove(book);
        return book.getId();
    }

    public Optional<Book> findOne(Long bookId) {
        return Optional.ofNullable(em.find(Book.class, bookId));
    }

    public List<Book> findAll(){
        return em.createQuery("select b from Book b", Book.class)
                .getResultList();
    }
}
