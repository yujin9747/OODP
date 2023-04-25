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
}
