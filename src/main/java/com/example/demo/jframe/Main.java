package com.example.demo.jframe;

import com.example.demo.domain.Book;
import com.example.demo.domain.Library;
import com.example.demo.domain.Role;
import com.example.demo.domain.Student;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.LibraryRepository;
import com.example.demo.repository.RentalInfoRepository;
import com.example.demo.repository.StudentRepository;

import javax.swing.*;

public class Main extends JFrame {
    static LibraryRepository libraryRepository = new LibraryRepository();
    static BookRepository bookRepository = new BookRepository();
    static StudentRepository studentRepository = new StudentRepository();
    static RentalInfoRepository rentalInfoRepository = new RentalInfoRepository();

    private static void initializeRepositories() {
        Library library = new Library(1L, "Handong Global University Library", 200);
        libraryRepository.getLibraryList().add(library);
        Book book = new Book(1L, "Introduction to Metaverse", 8972805491L, "510.32 지 474", "좋은 생각", 1L);
        bookRepository.getBookList().add(book);
        Student student = new Student(1L, "yujin", Role.STUDENT, "slsddbwls4421", library.getId(), 22000630);
        Student admin = new Student(2L, "admin", Role.ADMIN, "admin123", library.getId(), 21500683);
        studentRepository.getStudentList().add(student);
        studentRepository.getStudentList().add(admin);
    }
    public static void main(String[] args){
        initializeRepositories();
        new MainWindow(-1, studentRepository, bookRepository);
    }

}
