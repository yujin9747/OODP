package com.example.demo;

import com.example.demo.jframe.MainWindow;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
public class DemoApplication {

    static BookRepository bookRepository = new BookRepository();
    static StudentRepository studentRepository = new StudentRepository();

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        System.setProperty("java.awt.headless", "false"); //Disables headless
        SwingUtilities.invokeLater(() -> {
            new MainWindow(-1, studentRepository, bookRepository);
        });

    }

}
