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

    private String studentId;
    private LocalDateTime enableDate;
    private LocalDateTime enrolledDate;
    private LocalDateTime lastModifiedDate;


    public Student(String name, Role role, String pw, Library library, String sid) {
        super(name, role, pw, library);
        studentId = sid;
        enrolledDate = LocalDateTime.now();
        enableDate = null;
        lastModifiedDate = LocalDateTime.now();

    }
    public interface Builder{
        public Builder name(String name);

        public Builder role(Role role);

        public Builder pw(String pw);

        public Builder library(Library library);

        public Builder sid(String sid);

        public Student build();

    }
    public static class StudentBuilder implements Builder {

        private String name;
        private Role role;
        private String password;
        private Library library;
        private String sid;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder role(Role role) {
            this.role = role;
            return this;
        }

        public Builder pw(String pw) {
            this.password = pw;
            return this;
        }

        public Builder library(Library library) {
            this.library = library;
            return this;
        }

        public Builder sid(String sid) {
            this.sid = sid;
            return this;
        }

        public Student build() {
            return new Student(name, role, password, library, sid);
        }
    }
}

