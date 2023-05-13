package com.example.demo.domain;

// admin으로 로그인 시, id : 1130403, Pw : admin1234 

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@DiscriminatorValue("Admin")
public class Admin extends Member{

    private int weekTotalHours;
    private String adminId;
    private LocalDateTime enrolledDate;
    private LocalDateTime lastModifiedDate;

    public Admin(String name, Role role, String pw, Library library, String adminId, int weekTotalHours) {
        super(name, role, pw, library);
        this.adminId = adminId;
        this.weekTotalHours = weekTotalHours;
        this.enrolledDate = LocalDateTime.now();
        this.lastModifiedDate = LocalDateTime.now();

    }

    public Admin() {

    }
}
