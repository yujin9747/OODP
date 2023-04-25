package com.example.demo.repository;

import com.example.demo.domain.Member;
import com.example.demo.domain.Professor;
import com.example.demo.domain.Student;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Long delete(Member member) {
        em.remove(member);
        return member.getId();
    }

    public Optional<Member> findOne(Long memberId) {
        return Optional.ofNullable(em.find(Member.class, memberId));
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Professor> findAllProfessors(){
        return em.createQuery("select p from Professor p", Professor.class)
                .getResultList();
    }

    public List<Student> findAllStudents(){
        return em.createQuery("select s from Student s", Student.class)
                .getResultList();
    }
}
