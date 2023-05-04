package com.example.demo.repository;

import com.example.demo.domain.Member;
import com.example.demo.domain.RentalInfo;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class RentalInfoRepository {
    private final EntityManager em;

    public void save(RentalInfo rentalInfo) {
        em.persist(rentalInfo);
    }

    public RentalInfo findOne(Long rentalInfoId) {
        return em.createQuery("select r from RentalInfo r where id = :id", RentalInfo.class)
                .setParameter("id", rentalInfoId)
                .getSingleResult();
    }

    public RentalInfo findOneByMemberIdAndBookId(Long memberId, Long bookId) {
        return em.createQuery("select r from RentalInfo r where member.id = :memberId and book.id = :bookId", RentalInfo.class)
                .setParameter("memberId", memberId)
                .setParameter("bookId", bookId)
                .getSingleResult();
    }

    public Long delete(RentalInfo rentalInfo) {
        em.remove(rentalInfo);
        return rentalInfo.getId();
    }
}
