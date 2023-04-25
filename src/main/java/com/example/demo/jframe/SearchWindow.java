package com.example.demo.jframe;

import com.example.demo.BeanUtil;
import com.example.demo.domain.*;
import com.example.demo.service.BookService;
import com.example.demo.service.MemberService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class SearchWindow extends JFrame {

    private final MemberService memberService;
    private final BookService bookService;

    Button checkoutBTN;
    Button returnBTN;
    Button reservationBTN;

    Optional<Member> loginedMember;
    Optional<Book> searchedBook;
    int indexOfMember;
    int indexOfBook;

    public SearchWindow(int indexOfMember, int indexOfBook){
        this.indexOfMember = indexOfMember;
        this.indexOfBook = indexOfBook;

        this.memberService = BeanUtil.get(MemberService.class);
        this.bookService = BeanUtil.get(BookService.class);


        this.loginedMember = memberService.findOne((long)indexOfMember);
        this.searchedBook = bookService.findOne((long)indexOfBook);


        setTitle("Search 결과창"); //창 제목

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        Container c = getContentPane();

        c.setLayout(new GridLayout(8, 1));

        JLabel title = new JLabel();
        JLabel position = new JLabel();
        JLabel status = new JLabel();
        JLabel isbn = new JLabel();
        JLabel publisher = new JLabel();

        if(searchedBook.isPresent()) {
            title.setText("Title : " + searchedBook.get().getTitle());
            position.setText("Position : " + searchedBook.get().getPosition());
            if (searchedBook.get().isBorrowed()) status.setText("Status : 대출중");
            else if (searchedBook.get().isReserved()) status.setText("Status : 예약중");
            else status.setText("Status : 이용가능");
            isbn.setText("ISBN : " + searchedBook.get().getIsbn());
            publisher.setText("Publisdher : " + searchedBook.get().getPublisher());

            add(title);
            add(position);
            add(status);
            add(isbn);
            add(publisher);

            if (indexOfMember != -1 && loginedMember.get().getRole() != Role.ADMIN) {
                checkoutBTN = new Button("대출하기");
                checkoutBTN.setBounds(20, 5, 70, 30);
                returnBTN = new Button("반납하기");
                returnBTN.setBounds(20, 5, 70, 30);

                checkoutBTN.addActionListener(new SearchWindow.SearchActionListener());
                returnBTN.addActionListener(new SearchWindow.SearchActionListener());

                add(checkoutBTN);
                add(returnBTN);

                // Todo : Role.STUDENT, disabled=true인 상태에서 버튼 안나와야 하는데 나옴.
                if (loginedMember.get().isDisabled() == false) {
                    reservationBTN = new Button("예약하기");
                    reservationBTN.setBounds(20, 5, 70, 30);
                    reservationBTN.addActionListener(new SearchWindow.SearchActionListener());

                    add(reservationBTN);
                }
            }
        }
        else{
            // Todo : 검색 결과 없는 화면도 그리기
        }


        setSize(600, 600); //창 사이즈

        setVisible(true); //보이기


    }

    private class SearchActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("대출하기")) {
                if (loginedMember.get().getRole() == Role.STUDENT) {
//                    Student loginedStudent = (Student) loginedMember;
//
//                    Student editedMember = new Student(loginedStudent.getId(), loginedStudent.getName(), Role.STUDENT, loginedStudent.getPassword(), loginedStudent.getLibraryId(), loginedStudent.getStudentId());
//
//                    editedMember.setLastModifiedDate(LocalDateTime.now());
//                    LocalDateTime longestDueDate = null;
//                    List<RentalInfo> rentalInfoList = rentalInfoRepository.getRentalInfoList();
//                    for(int i=0; i<rentalInfoList.size(); i++){
//                        if(rentalInfoList.get(i).getMemberId() == loginedStudent.getMemberId() && rentalInfoList.get(i).isReturned() == false){
//                            longestDueDate = (longestDueDate.isAfter(rentalInfoList.get(i).getReturnDueDate())) ? rentalInfoList.get(i).getReturnDueDate(): longestDueDate;
//                        }
//                    }

//                    memberRepository.getStudentList().add(editedMember);
//                    memberRepository.getStudentList().remove(loginedMember);

//                    RentalInfo rentalInfo = new RentalInfo((long) rentalInfoRepository.getRentalInfoList().size(), Role.STUDENT, loginedStudent.getId(), searchedBook.getId());
//                    rentalInfoRepository.getRentalInfoList().add(rentalInfo);
//
//                    Book editiedBook = new Book(searchedBook.getId(), searchedBook.getTitle(), searchedBook.getIsbn(), searchedBook.getPosition(), searchedBook.getPublisher(), searchedBook.getLibraryId());
//                    editiedBook.setEnrolledDate(searchedBook.getEnrolledDate());
//                    editiedBook.setBorrowed(true);
//                    editiedBook.setLastModifiedDate(LocalDateTime.now());
//                    bookRepository.getBookList().add(editiedBook);
//                    bookRepository.getBookList().remove(searchedBook);
//
//                    //memberRepository.getStudentList().stream().forEach(s -> System.out.println("Student lastModifiedDate " + s.getLastModifiedDate()));
//                    bookRepository.getBookList().stream().forEach(b -> System.out.println("Book Borrowed status : " + b.isBorrowed()));
//                    rentalInfoRepository.getRentalInfoList().stream().forEach(i -> System.out.println("Rental Info (memberId, bookId): " + i.getMemberId() + ", " + i.getBookId()));

                    JOptionPane.showMessageDialog(null, "대출이 완료되었습니다.");

                    // Todo: 화면 refresh하기
                } else if (loginedMember.get().getRole() == Role.PROFESSOR) {

                }

            } else if (command.equals("반납하기")) {
                if (loginedMember.get().getRole() == Role.STUDENT) {
//                    Student loginedStudent = (Student) loginedMember;
//
//                    Student editedMember = new Student(loginedStudent.getId(), loginedStudent.getName(), Role.STUDENT, loginedStudent.getPassword(), loginedStudent.getLibraryId(), loginedStudent.getStudentId());
//
//                    editedMember.setLastModifiedDate(LocalDateTime.now());
//                    memberRepository.getStudentList().add(editedMember);
//                    memberRepository.getStudentList().remove(loginedMember);

//                    RentalInfo rentalInfo = null;
//                    List<RentalInfo> rentalInfoList = rentalInfoRepository.getRentalInfoList();
//                    for (int i = 0; i < rentalInfoList.size(); i++){
//                        if (rentalInfoList.get(i).getMemberId() == loginedStudent.getId() && rentalInfoList.get(i).getBookId() == searchedBook.getId()) {
//                            rentalInfo = rentalInfoList.get(i);
//                        }
//                    }
//                    RentalInfo returnInfo = rentalInfo.returnInfo();
//                    rentalInfoRepository.getRentalInfoList().add(returnInfo);
//                    rentalInfoRepository.getRentalInfoList().remove(rentalInfo);
//
//                    Book editiedBook = new Book(searchedBook.getId(), searchedBook.getTitle(), searchedBook.getIsbn(), searchedBook.getPosition(), searchedBook.getPublisher(), searchedBook.getLibraryId());
//                    editiedBook.setEnrolledDate(searchedBook.getEnrolledDate());
//                    editiedBook.setBorrowed(false);
//                    editiedBook.setLastModifiedDate(LocalDateTime.now());
//                    bookRepository.getBookList().add(editiedBook);
//                    bookRepository.getBookList().remove(searchedBook);
//
//                    //memberRepository.getStudentList().stream().forEach(s -> System.out.println("Student lastModifiedDate " + s.getLastModifiedDate()));
//                    bookRepository.getBookList().stream().forEach(b -> System.out.println("Book borrowed status(기댓값 : false) : " + b.isBorrowed()));
//                    rentalInfoRepository.getRentalInfoList().stream().forEach(ri -> System.out.println("Return Info (memberId, bookId): " + ri.getMemberId() + ", " + ri.getBookId()));

                    JOptionPane.showMessageDialog(null, "반납이 완료되었습니다.");

                    // Todo: 화면 refresh하기
                }
                else if(loginedMember.get().getRole() == Role.PROFESSOR){

                }
            } else if (command.equals("예약하기")) {

            }

        }

    }
}
