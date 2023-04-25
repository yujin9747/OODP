package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@DiscriminatorValue("Student")
public class Student extends Member{

    private int studentId;
    private LocalDateTime enableDate;
    private LocalDateTime enrolledDate;
    private LocalDateTime lastModifiedDate;


    public Student(String name, Role role, String pw, long libraryId, int sid) {
        super(name, role, pw, libraryId);
        studentId = sid;
        enrolledDate = LocalDateTime.now();
        enableDate = null;
        lastModifiedDate = LocalDateTime.now();

    }
}

