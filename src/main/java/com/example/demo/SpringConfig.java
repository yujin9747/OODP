package com.example.demo;

import com.example.demo.jframe.MainWindow;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@RequiredArgsConstructor
public class SpringConfig {

    private final EntityManager em;

    @Bean
    public MemberRepository memberRepository(){
        return new MemberRepository(em);
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public BookRepository bookRepository(){
        return new BookRepository(em);
    }

    @Bean
    public BookService bookService(){
        return new BookService(bookRepository());
    }

    @Bean
    public LibraryRepository libraryRepository(){
        return new LibraryRepository(em);
    }

    @Bean
    public LibraryService libraryService(){
        return new LibraryService(libraryRepository());
    }

    @Bean
    public RentalInfoRepository rentalInfoRepository(){
        return new RentalInfoRepository(em);
    }

    @Bean
    public RentalInfoService rentalInfoService(){
        return new RentalInfoService(rentalInfoRepository(), memberRepository(), bookRepository());
    }

    @Bean
    public ReservationInfoRepository reservationInfoRepository(){
        return new ReservationInfoRepository(em);
    }

    @Bean
    public ReservationInfoService reservationInfoService(){
        return new ReservationInfoService(reservationInfoRepository());
    }
}
