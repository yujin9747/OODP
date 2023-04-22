package com.example.demo;

import com.example.demo.jframe.LoginWindow;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginWindow.class, args);
    }

}
