package com.example.demo.service;

import com.example.demo.domain.*;
import com.example.demo.jframe.MainWindow;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.RentalInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.command.Command;

import javax.swing.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.TemporalAmount;
import java.util.Iterator;
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
    public List<RentalInfo> findRentalInfosByBookId(Long bookId) {
        return rentalInfoRepository.findRentalInfosByBookId(bookId);
    }
    public boolean isTheBookBorrowed(Book book) {
        List<RentalInfo> rentalInfoList = findRentalInfosByBookId(book.getId());
        RentalInfoContainer rentalInfoContainer = new RentalInfoContainer(rentalInfoList);

        for (Iterator it = rentalInfoContainer.getIterator(); it.hasNext();) {
            RentalInfo rentalInfo = (RentalInfo) it.next();
            if(!rentalInfo.isReturned()) {
                return true;
            }
        }

        return false;
    }

    public int updateRentalInfoDueDate(Long rentalInfoId) {
        LocalDateTime dueDate = findOne(rentalInfoId).getReturnDueDate().plusDays(5);
        return rentalInfoRepository.updateRentalInfoDueDate(rentalInfoId, dueDate);
    }

    //대출하기
    public void checkout( Member loginedMember, Book searchedBook) {
        if (loginedMember.getRole() == Role.STUDENT) {
            if ((loginedMember.getLibrary().getId() == searchedBook.getLibrary().getId()) || loginedMember.isExternalLibraryPermission()) {
                saveRentalInfo(loginedMember.getId(), searchedBook.getId());
                JOptionPane.showMessageDialog(null, "대출이 완료되었습니다.");
            } else {
                JOptionPane.showMessageDialog(null, "외부도서에 대한 접근이 허가되지 않았습니다.");
            }


            new MainWindow(loginedMember);
//            setVisible(false);
        } else if (loginedMember.getRole() == Role.PROFESSOR) {

        }
    }

    public void return_book( Member loginedMember, Book searchedBook) {
        if (loginedMember.getRole() == Role.STUDENT) {
            returnBook(loginedMember.getId(), searchedBook.getId());
            JOptionPane.showMessageDialog(null, "반납이 완료되었습니다.");

            new MainWindow(loginedMember);
//            setVisible(false);
        }
        else if(loginedMember.getRole() == Role.PROFESSOR){

        }
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

    }
}
