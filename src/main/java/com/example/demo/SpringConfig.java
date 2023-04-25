//package com.example.demo;
//
//import com.example.demo.repository.MemberRepository;
//import com.example.demo.service.MemberService;
//import jakarta.persistence.EntityManager;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
//@Configuration
//@RequiredArgsConstructor
//public class SpringConfig {
//
//    private final EntityManager em;
//
//    @Bean
//    public MemberRepository memberRepository(){
//        return new MemberRepository(em);
//    }
//
//    @Bean
//    public MemberService memberService(){
//        return new MemberService(memberRepository());
//    }
//}
