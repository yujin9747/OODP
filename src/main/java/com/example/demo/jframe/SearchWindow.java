package com.example.demo.jframe;

import com.example.demo.domain.*;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.LibraryRepository;
import com.example.demo.repository.RentalInfoRepository;
import com.example.demo.repository.StudentRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;

public class SearchWindow extends JFrame {
    static LibraryRepository libraryRepository = new LibraryRepository();
    static BookRepository bookRepository = new BookRepository();
    static StudentRepository studentRepository = new StudentRepository();
    static RentalInfoRepository rentalInfoRepository = new RentalInfoRepository();

    Button checkoutBTN;
    Button returnBTN;
    Button reservationBTN;

    Member loginedMember;
    Book searchedBook;
    int indexOfMember;
    int indexOfBook;

    public SearchWindow(int indexOfMember, int indexOfBook){
        this.indexOfMember = indexOfMember;
        this.indexOfBook = indexOfBook;

        this.loginedMember = (indexOfMember == -1) ? null : studentRepository.getStudentList().get(indexOfMember);
        this.searchedBook = bookRepository.getBookList().get(indexOfBook);

        setTitle("Search 결"); //창 제목

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        Container c = getContentPane();

        c.setLayout(new FlowLayout());

        if(indexOfMember != -1 && loginedMember.getRole() != Role.ADMIN){
            checkoutBTN = new Button("대출하기");
            checkoutBTN.setBounds(20, 5, 70, 30);
            returnBTN = new Button("반납하기");
            returnBTN.setBounds(20, 5, 70, 30);

            checkoutBTN.addActionListener(new SearchWindow.SearchActionListener());
            returnBTN.addActionListener(new SearchWindow.SearchActionListener());

            add(checkoutBTN);
            add(returnBTN);

            // Todo : Role.STUDENT, disabled=true인 상태에서 버튼 안나와야 하는데 나옴.
            if(loginedMember.isDisabled() == false){
                reservationBTN = new Button("예약하기");
                reservationBTN.setBounds(20, 5, 70, 30);
                reservationBTN.addActionListener(new SearchWindow.SearchActionListener());

                add(reservationBTN);
            }
        }

        setSize(600, 600); //창 사이즈

        setVisible(true); //보이기


    }

    private class SearchActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("대출하기")) {
                if (loginedMember.getRole() == Role.STUDENT) {
                    Student loginedStudent = (Student) loginedMember;

                    Student editedMember = new Student(loginedStudent.getMemberId(), loginedStudent.getName(), Role.STUDENT, loginedStudent.getPassword(), loginedStudent.getLibraryId(), loginedStudent.getStudentId());

                    editedMember.setLastModifiedDate(LocalDateTime.now());
//                    LocalDateTime longestDueDate = null;
//                    List<RentalInfo> rentalInfoList = rentalInfoRepository.getRentalInfoList();
//                    for(int i=0; i<rentalInfoList.size(); i++){
//                        if(rentalInfoList.get(i).getMemberId() == loginedStudent.getMemberId() && rentalInfoList.get(i).isReturned() == false){
//                            longestDueDate = (longestDueDate.isAfter(rentalInfoList.get(i).getReturnDueDate())) ? rentalInfoList.get(i).getReturnDueDate(): longestDueDate;
//                        }
//                    }

                    studentRepository.getStudentList().add(editedMember);
                    studentRepository.getStudentList().remove(loginedMember);

                    RentalInfo rentalInfo = new RentalInfo((long) rentalInfoRepository.getRentalInfoList().size(), Role.STUDENT, loginedStudent.getMemberId(), searchedBook.getBookId());
                    rentalInfoRepository.getRentalInfoList().add(rentalInfo);

                    Book editiedBook = new Book(searchedBook.getBookId(), searchedBook.getTitle(), searchedBook.getIsbn(), searchedBook.getPosition(), searchedBook.getPublisher(), searchedBook.getLibraryId());
                    editiedBook.setEnrolledDate(searchedBook.getEnrolledDate());
                    editiedBook.setBorrowed(true);
                    editiedBook.setLastModifiedDate(LocalDateTime.now());
                    bookRepository.getBookList().add(editiedBook);
                    bookRepository.getBookList().remove(searchedBook);

                    studentRepository.getStudentList().stream().forEach(s -> System.out.println("Student lastModifiedDate " + s.getLastModifiedDate()));
                    bookRepository.getBookList().stream().forEach(b -> System.out.println("Book Borrowed status : " + b.isBorrowed()));
                    rentalInfoRepository.getRentalInfoList().stream().forEach(i -> System.out.println("Rental Info (memberId, bookId): " + i.getMemberId() + ", " + i.getBookId()));
                } else if (loginedMember.getRole() == Role.PROFESSOR) {

                }

            } else if (command.equals("반납하기")) {
                if (loginedMember.getRole() == Role.STUDENT) {
                    Student loginedStudent = (Student) loginedMember;

                    Student editedMember = new Student(loginedStudent.getMemberId(), loginedStudent.getName(), Role.STUDENT, loginedStudent.getPassword(), loginedStudent.getLibraryId(), loginedStudent.getStudentId());

                    editedMember.setLastModifiedDate(LocalDateTime.now());
                    studentRepository.getStudentList().add(editedMember);
                    studentRepository.getStudentList().remove(loginedMember);

                    RentalInfo rentalInfo = null;
                    List<RentalInfo> rentalInfoList = rentalInfoRepository.getRentalInfoList();
                    for (int i = 0; i < rentalInfoList.size(); i++){
                        if (rentalInfoList.get(i).getMemberId() == loginedStudent.getMemberId() && rentalInfoList.get(i).getBookId() == searchedBook.getBookId()) {
                            rentalInfo = rentalInfoList.get(i);
                        }
                    }
                    RentalInfo returnInfo = rentalInfo.returnInfo();
                    rentalInfoRepository.getRentalInfoList().add(returnInfo);
                    rentalInfoRepository.getRentalInfoList().remove(rentalInfo);

                    Book editiedBook = new Book(searchedBook.getBookId(), searchedBook.getTitle(), searchedBook.getIsbn(), searchedBook.getPosition(), searchedBook.getPublisher(), searchedBook.getLibraryId());
                    editiedBook.setEnrolledDate(searchedBook.getEnrolledDate());
                    editiedBook.setBorrowed(false);
                    editiedBook.setLastModifiedDate(LocalDateTime.now());
                    bookRepository.getBookList().add(editiedBook);
                    bookRepository.getBookList().remove(searchedBook);

                    studentRepository.getStudentList().stream().forEach(s -> System.out.println("Student lastModifiedDate " + s.getLastModifiedDate()));
                    bookRepository.getBookList().stream().forEach(b -> System.out.println("Book borrowed status(기댓값 : false) : " + b.isBorrowed()));
                    rentalInfoRepository.getRentalInfoList().stream().forEach(ri -> System.out.println("Return Info (memberId, bookId): " + ri.getMemberId() + ", " + ri.getBookId()));
                }
                else if(loginedMember.getRole() == Role.PROFESSOR){

                }
            } else if (command.equals("예약하기")) {

            }

        }

    }
    public static void main(String[] args) {
        Library library = new Library(1L, "Handong Global University Library", 200);
        libraryRepository.getLibraryList().add(library);
        Book book = new Book(1L, "Introduction to Metaverse", 8972805491L, "510.32 지 474", "좋은 생각", 1L);
        bookRepository.getBookList().add(book);
        Student student = new Student(1L, "yujin", Role.STUDENT, "slsddbwls4421", library.getLibraryId(), 22000630);
        studentRepository.getStudentList().add(student);
        new SearchWindow(0, 0);
    }
}
