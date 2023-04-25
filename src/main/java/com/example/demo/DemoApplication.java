package com.example.demo;

import com.example.demo.domain.Member;
import com.example.demo.domain.ReservationInfo;
import com.example.demo.jframe.MainWindow;
import com.example.demo.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
@SpringBootApplication
public class DemoApplication {
    
    private static MemberService memberService = null;
    private static BookService bookService = null;
    private static LibraryService libraryService = null;
    private static RentalInfoService rentalInfoService = null;
    private static ReservationInfoService reservationInfoService = null;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        System.setProperty("java.awt.headless", "false"); //Disables headless
        SwingUtilities.invokeLater(() -> {
            new MainWindow(memberService, bookService, libraryService, rentalInfoService, reservationInfoService);
        });
    }

    public DemoApplication(MemberService memberService) {
        this.memberService = memberService;
    }

    public Optional<Member> findOneMember(Long memberId){
        return memberService.findOne(memberId);
    }

}
