package com.example.demo.service;

import com.example.demo.domain.Book;
import com.example.demo.domain.Member;
import com.example.demo.domain.RentalInfo;
import com.example.demo.domain.ReservationInfo;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ReservationInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReservationInfoService {

    private final ReservationInfoRepository reservationInfoRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    @Transactional
    public void saveReservationInfo(Long memberId, Long bookId){
        Optional<Member> member = memberRepository.findOne(memberId);
        Optional<Book> book = bookRepository.findOne(bookId);

        ReservationInfo reservationInfo = new ReservationInfo(member.get(), book.get());
        reservationInfoRepository.save(reservationInfo);

    }

    public ReservationInfo findOne(Long reservationInfoId){ return reservationInfoRepository.findOne(reservationInfoId);}
    public ReservationInfo findOneByBookId(Long bookId){return reservationInfoRepository.findOneByBookId(bookId);}
    public Long cancelReservation(ReservationInfo reservationInfo){return reservationInfoRepository.delete(reservationInfo);}
}
