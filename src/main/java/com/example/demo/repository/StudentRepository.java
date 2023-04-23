package com.example.demo.repository;

import com.example.demo.domain.Role;
import com.example.demo.domain.Student;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class StudentRepository {
    List<Student> studentList = new ArrayList<>();
}
