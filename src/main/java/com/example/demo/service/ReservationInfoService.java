package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.domain.ReservationInfo;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ReservationInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReservationInfoService {

    private final ReservationInfoRepository reservationInfoRepository;

    @Transactional
    public void saveReservationInfo(ReservationInfo reservationInfo){
        reservationInfoRepository.save(reservationInfo);
    }
}
