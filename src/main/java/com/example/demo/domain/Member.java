package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public abstract class Member {
    private Long memberId;
    private String name;
    private Enum<Role> role;
    private String password;
    private Long libraryId;
    private boolean disabled;


    public Member(long memberId, String name, Role role, String pw, long libraryId) {
        this.memberId = memberId;
        this.name = name;
        this.role = role;
        this.password = pw;
        this.libraryId = libraryId;
        this.disabled = false;
    }
}
