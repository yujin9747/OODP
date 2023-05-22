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
}
