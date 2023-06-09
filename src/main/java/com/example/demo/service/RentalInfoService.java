package com.example.demo.service;

import com.example.demo.builder.concreteMainBuilder.MainWindowUserBuilder;
import com.example.demo.builder.director.MainWindowDirector;
import com.example.demo.domain.*;
import com.example.demo.iterator.Iterator;
import com.example.demo.iterator.RentalInfoContainer;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.RentalInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Optional;

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
    public void checkout( Member loginedMember, Book searchedBook, JFrame window) {
        if (loginedMember.getRole() == Role.STUDENT) {
            if ((loginedMember.getLibrary().getId() == searchedBook.getLibrary().getId()) || loginedMember.isExternalLibraryPermission()) {
                RentalInfo rentalInfo = rentalInfoRepository.findOneByMemberIdAndBookId(loginedMember.getId(), searchedBook.getId());
                if(rentalInfo == null) {
                    saveRentalInfo(loginedMember.getId(), searchedBook.getId());
                    JOptionPane.showMessageDialog(null, "대출이 완료되었습니다.");
                }
                else {
                    rentalInfoRepository.updateRetalInfoCheckout(loginedMember, searchedBook);
                    JOptionPane.showMessageDialog(null, "대출이 완료되었습니다.(이전 대출 기록 존재)");
                }

            } else {
                JOptionPane.showMessageDialog(null, "외부도서에 대한 접근이 허가되지 않았습니다.");
            }
            MainWindowUserBuilder builder = new MainWindowUserBuilder();
            MainWindowDirector director = new MainWindowDirector(builder, loginedMember);
            director.constructMainWindow();
            window.setVisible(false);
        } else if (loginedMember.getRole() == Role.PROFESSOR) {

        }
    }

    @Transactional
    public void return_book( Member loginedMember, Book searchedBook, JFrame window) {
        if (loginedMember.getRole() == Role.STUDENT) {
            returnBook(loginedMember.getId(), searchedBook.getId());
            JOptionPane.showMessageDialog(null, "반납이 완료되었습니다.");

            MainWindowUserBuilder builder = new MainWindowUserBuilder();
            MainWindowDirector director = new MainWindowDirector(builder, loginedMember);
            director.constructMainWindow();
            window.setVisible(false);
        }
        else if(loginedMember.getRole() == Role.PROFESSOR){

        }
    }

    @Transactional
    public void returnBook(Long memberId, Long bookId) {
        //command DP 적용시켰을 떄 db에서 반납처리가 안됨, 기존에는 해당 function을 searchPage에서 호출했고,
        // command DP 적용 후에는 return_book function을 통해 ReturnCommand에서 호출하는 것 이외에 해당 function 관련 변경된 사항 없음
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
