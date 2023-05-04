package com.example.demo.service;

import com.example.demo.domain.Book;
import com.example.demo.domain.Member;
import com.example.demo.domain.RentalInfo;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.RentalInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RentalInfoService {

    private final RentalInfoRepository rentalInfoRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;



    @Transactional
    public void saveRentalInfo(Long memberId, Long bookId){
        Optional<Member> member = memberRepository.findOne(memberId);
        Optional<Book> book = bookRepository.findOne(bookId);

        RentalInfo rentalInfo = new RentalInfo(member.get(), book.get());
        rentalInfoRepository.save(rentalInfo);
    }

    public RentalInfo findOne(Long rentalInfoId){ return rentalInfoRepository.findOne(rentalInfoId);}
    public RentalInfo findOneByMemberIdAndBookId(Long memberId, Long bookId){return rentalInfoRepository.findOneByMemberIdAndBookId(memberId, bookId);}
    public List<RentalInfo> findRentalInfosByMemberId(Long memberId){
        return rentalInfoRepository.findRentalInfosByMemberId(memberId);
    }

    public int updateRentalInfoDueDate(Long rentalInfoId) {
        LocalDateTime dueDate = findOne(rentalInfoId).getReturnDueDate().plusDays(5);
        return rentalInfoRepository.updateRentalInfoDueDate(rentalInfoId, dueDate);
    }


    @Transactional
    public void returnBook(Long memberId, Long bookId) {
        Optional<Member> member = memberRepository.findOne(memberId);
        Optional<Book> book = bookRepository.findOne(bookId);
        book.get().setBorrowed(false);
        book.get().setLastModifiedDate(LocalDateTime.now());

        RentalInfo rentalInfo = rentalInfoRepository.findOneByMemberIdAndBookId(memberId, bookId);
        rentalInfoRepository.delete(rentalInfo);
    }
}
