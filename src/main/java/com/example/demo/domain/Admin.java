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
    public interface Builder{
        public Builder name(String name);

        public Builder role(Role role);

        public Builder pw(String pw);

        public Builder library(Library library);

        public Builder adminId(String adminId);

        public Builder weekTotalHours(int weekTotalHours);

        public Admin build();

    }
    public static class AdminBuilder implements Builder{

        private String name;
        private Role role;
        private String password;
        private Library library;
        private String adminId;
        private int weekTotalHours;
        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder role(Role role){
            this.role = role;
            return this;
        }
        public Builder pw(String pw){
            this.password = pw;
            return this;
        }
        public Builder library(Library library){
            this.library = library;
            return this;
        }
        public Builder adminId(String adminId){
            this.adminId = adminId;
            return this;
        }

        public Builder weekTotalHours(int weekTotalHours){
            this.weekTotalHours = weekTotalHours;
            return this;
        }
        public Admin build() {
            return new Admin(name,role,password, library, adminId,weekTotalHours);
        }

    }
    public Admin() {

    }
}
