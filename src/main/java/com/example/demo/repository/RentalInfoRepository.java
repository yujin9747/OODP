package com.example.demo.repository;

import com.example.demo.domain.Book;
import com.example.demo.domain.Member;
import com.example.demo.domain.RentalInfo;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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

    public RentalInfo findOneByMemberIdAndBookId(Long memberId, Long bookId) {// error, 한 멤버가 같은 책을 두번 빌렸을 경우 에러 발생
        try {
            return em.createQuery("select r from RentalInfo r where member.id = :memberId and book.id = :bookId", RentalInfo.class)
                    .setParameter("memberId", memberId)
                    .setParameter("bookId", bookId)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    public List<RentalInfo> findRentalInfosByMemberId(Long memberId){
        return em.createQuery("select r from RentalInfo r where member.id = :memberId", RentalInfo.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    public List<RentalInfo> findRentalInfosByBookId(Long bookId){
        return em.createQuery("select r from RentalInfo r where book.id = :bookId", RentalInfo.class)
                .setParameter("bookId", bookId)
                .getResultList();
    }

    public int updateRentalInfoDueDate(Long rentalInfoId, LocalDateTime dueDate) {
        return em.createQuery("update RentalInfo r set r.returnDueDate = :dueDate where r.id = :id")
                .setParameter("dueDate", dueDate)
                .setParameter("id", rentalInfoId)
                .executeUpdate();
    }

    public Long delete(RentalInfo rentalInfo) {
        em.remove(rentalInfo);
        return rentalInfo.getId();
    }

    public void updateRetalInfoCheckout(Member loginedMember, Book searchedBook) {
        em.createQuery("update RentalInfo r set r.isReturned = false, r.returnDueDate = (CURRENT DATE + 14), r.rentalDate = current date, r.returnedDate = null where r.member = :loginedMember and r.book = :searchedBook")
                .setParameter("loginedMember", loginedMember)
                .setParameter("searchedBook", searchedBook)
                .executeUpdate();
    }
}
