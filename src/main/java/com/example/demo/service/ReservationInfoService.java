package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.domain.RentalInfo;
import com.example.demo.domain.ReservationInfo;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ReservationInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReservationInfoService {

    private final ReservationInfoRepository reservationInfoRepository;

    @Transactional
    public void saveReservationInfo(ReservationInfo reservationInfo){
        reservationInfoRepository.save(reservationInfo);
    }

    public ReservationInfo findOne(Long reservationInfoId){ return reservationInfoRepository.findOne(reservationInfoId);}
    public ReservationInfo findOneByMemberIdAndBookId(Long memberId, Long bookId){return reservationInfoRepository.findOneByMemberIdAndBookId(memberId, bookId);}
    public Long cancelReservation(ReservationInfo reservationInfo){return reservationInfoRepository.delete(reservationInfo);}
}
