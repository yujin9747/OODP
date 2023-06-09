package com.example.demo.repository;

import com.example.demo.domain.RentalInfo;
import com.example.demo.domain.ReservationInfo;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
public class ReservationInfoRepository {
    private final EntityManager em;

    public void save(ReservationInfo reservationInfo) {
        em.persist(reservationInfo);
    }


    public ReservationInfo findOne(Long reservationInfoId) {
        return em.createQuery("select r from ReservationInfo r where id = :id", ReservationInfo.class)
                .setParameter("id", reservationInfoId)
                .getSingleResult();
    }

    public ReservationInfo findOneByBookId(Long bookId) {
        try {
            return em.createQuery("select r from ReservationInfo r where book.id = :bookId", ReservationInfo.class)
                    .setParameter("bookId", bookId)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println("No result for the book from reservation info");
            return null;
        }

    }


    public void delete(Long reservationInfoId) {
        em.createQuery("delete from ReservationInfo r where r.id = :id")
                .setParameter("id", reservationInfoId)
                .executeUpdate();
    }
}
