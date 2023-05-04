package com.example.demo.service;

import com.example.demo.domain.*;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.RentalInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.TemporalAmount;
import java.util.List;
import java.util.Optional;
import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RentalInfoService {

    private final RentalInfoRepository rentalInfoRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;



    @Transactional
    public RentalInfo saveRentalInfo(Long memberId, Long bookId){
        Optional<Member> member = memberRepository.findOne(memberId);
        Optional<Book> book = bookRepository.findOne(bookId);

        RentalInfo rentalInfo = new RentalInfo(member.get(), book.get());
        rentalInfoRepository.save(rentalInfo);
        return rentalInfo;
    }

    public RentalInfo findOne(Long rentalInfoId){ return rentalInfoRepository.findOne(rentalInfoId);}
    public RentalInfo findOneByMemberIdAndBookId(Long memberId, Long bookId){return rentalInfoRepository.findOneByMemberIdAndBookId(memberId, bookId);}
    public List<RentalInfo> findRentalInfosByMemberId(Long memberId){
        return rentalInfoRepository.findRentalInfosByMemberId(memberId);
    }


    @Transactional
    public void returnBook(Long memberId, Long bookId) {
        Optional<Member> member = memberRepository.findOne(memberId);
        Optional<Book> book = bookRepository.findOne(bookId);
        book.get().setBorrowed(false);
        book.get().setLastModifiedDate(LocalDateTime.now());

        RentalInfo rentalInfo = rentalInfoRepository.findOneByMemberIdAndBookId(memberId, bookId);
        rentalInfo.setReturned(true);
        rentalInfo.setReturnedDate(LocalDateTime.now());

        if(rentalInfo.getReturnedDate().isAfter(rentalInfo.getReturnDueDate())){
            rentalInfo.setOverdue(true);
            rentalInfo.setOverDueDays(Period.between(rentalInfo.getReturnDueDate().toLocalDate(), rentalInfo.getReturnedDate().toLocalDate()).getDays());

            if(member.get().getRole() == Role.STUDENT){
                Student student = (Student) member.get();
                student.setLastModifiedDate(LocalDateTime.now());
                student.setDisabled(true);
                student.setEnableDate(LocalDateTime.now().plusDays(rentalInfo.getOverDueDays()));
            }
            else if(member.get().getRole() == Role.PROFESSOR){

            }

        }

        //rentalInfoRepository.delete(rentalInfo);
    }
}
