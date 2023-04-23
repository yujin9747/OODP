package com.example.demo.jframe;

import com.example.demo.domain.*;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.LibraryRepository;
import com.example.demo.repository.StudentRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class SearchWindow extends JFrame {
    static LibraryRepository libraryRepository = new LibraryRepository();
    static BookRepository bookRepository = new BookRepository();
    static StudentRepository studentRepository = new StudentRepository();

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
                studentRepository.getStudentList().remove(loginedMember);
                Member editedMember;
                if(loginedMember.getRole() == Role.STUDENT){
                    editedMember = new Student();
                }

            } else if (command.equals("반납하기")) {

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
