package com.example.demo.repository;

import com.example.demo.domain.Student;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class StudentRepository {
    private final List<Student> studentList = new ArrayList<>();
}
