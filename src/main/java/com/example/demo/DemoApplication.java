package com.example.demo;

import com.example.demo.domain.Member;
import com.example.demo.jframe.MainWindow;
import com.example.demo.service.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
@SpringBootApplication
public class DemoApplication {
    
    private static MemberService memberService;
    private static BookService bookService;
    private static LibraryService libraryService;
    private static RentalInfoService rentalInfoService;
    private static ReservationInfoService reservationInfoService;
    private final ApplicationContext context;	// 2. 실제 applicationContext 를 주입받는다.


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        System.setProperty("java.awt.headless", "false"); //Disables headless
        SwingUtilities.invokeLater(() -> {
            new MainWindow();
        });
    }

    @PostConstruct
    public void init(){
        BeanUtil.init(context); // 3. 객체의 생명주기를 이용해 스태틱 참조변수에 주입한다.
    }

//    public DemoApplication(MemberService memberService, BookService bookService, LibraryService libraryService, RentalInfoService rentalInfoService, ReservationInfoService reservationInfoService) {
//        this.memberService = memberService;
//        this.bookService = bookService;
//        this.libraryService = libraryService;
//        this.rentalInfoService = rentalInfoService;
//        this.reservationInfoService = reservationInfoService;
//    }

}
