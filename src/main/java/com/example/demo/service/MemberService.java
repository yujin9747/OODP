package com.example.demo.service;

import com.example.demo.domain.Admin;
import com.example.demo.domain.Member;
import com.example.demo.domain.Professor;
import com.example.demo.domain.Student;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
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

    public List<Admin> findAdmins() {
        return memberRepository.findAllAdmins();
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public List<Student> findOverdueStudents() {
        return memberRepository.findAllOverdueStudents();
    }

    public List<Student> findPermittedStudents() {
        return memberRepository.findPermittedStudents();
    }
    public List<Student> findNotPermittedStudents() {
        return memberRepository.findNotPermittedStudents();
    }

    public List<Student> findByStudentId(String selectedUserId) {
        return memberRepository.findByStudentId(selectedUserId);
    }

    @Transactional
    public void permitExternalLibrary(Long memberId) {
        Member member = memberRepository.findOne(memberId).get();
        memberRepository.permitById(member.getId());
    }

    @Transactional
    public void banExternalLibrary(Long memberId) {
        Member member = memberRepository.findOne(memberId).get();
        memberRepository.banById(member.getId());
    }
}
