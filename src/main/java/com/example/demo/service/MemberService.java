package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.domain.Professor;
import com.example.demo.domain.Student;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void saveMember(Member member){
        memberRepository.save(member);
    }

    public List<Student> findStudents(){
        return memberRepository.findAllStudents();
    }

    public List<Professor> findProfessors(){
        return memberRepository.findAllProfessors();
    }

    public Optional<Member> findOne(Long id){
        return memberRepository.findOne(id);
    }
}
