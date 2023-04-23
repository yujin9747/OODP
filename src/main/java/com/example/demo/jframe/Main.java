package com.example.demo.jframe;

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

    public static void main(String[] args){

        new MainWindow(-1);
    }

}
