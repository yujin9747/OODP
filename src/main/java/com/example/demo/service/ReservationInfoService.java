package com.example.demo.service;

import com.example.demo.builder.concreteMainBuilder.MainWindowUserBuilder;
import com.example.demo.builder.director.MainWindowDirector;
import com.example.demo.domain.Book;
import com.example.demo.domain.Member;
import com.example.demo.domain.ReservationInfo;
import com.example.demo.domain.Role;
import com.example.demo.jframe.MainWindow;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ReservationInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
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

    public void reserve(Member loginedMember, Book searchedBook) {
        if (loginedMember.getRole() == Role.STUDENT) {
            if ((loginedMember.getLibrary().getId() == searchedBook.getLibrary().getId()) || loginedMember.isExternalLibraryPermission()) {
                saveReservationInfo(loginedMember.getId(), searchedBook.getId());
                JOptionPane.showMessageDialog(null, "예약되었습니다.");
            } else {
                JOptionPane.showMessageDialog(null, "외부도서에 대한 접근이 허가되지 않았습니다.");
            }

            MainWindowUserBuilder builder = new MainWindowUserBuilder();
            MainWindowDirector director = new MainWindowDirector(builder, loginedMember);
            director.constructMainWindow();

//            new MainWindow(loginedMember);
//            setVisible(false); 창 닫기 안됨
        } else if (loginedMember.getRole() == Role.PROFESSOR) {
        }
    }

    public void reserveCancel(ReservationInfo reservationInfo, Member loginedMember, Book searchedBook) {
        if (loginedMember.getRole() == Role.STUDENT) {
            cancelReservation(reservationInfo.getId());
            JOptionPane.showMessageDialog(null, "예약이 취소되었습니다.");

            MainWindowUserBuilder builder = new MainWindowUserBuilder();
            MainWindowDirector director = new MainWindowDirector(builder, loginedMember);
            director.constructMainWindow();
//            new MainWindow(loginedMember);
//            setVisible(false); 창 닫기 안됨
        } else if (loginedMember.getRole() == Role.PROFESSOR) {

        }
    }

    public void cancelReservation(Long reservationInfoId){reservationInfoRepository.delete(reservationInfoId);}
}
