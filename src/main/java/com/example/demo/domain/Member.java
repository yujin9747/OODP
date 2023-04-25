package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
public abstract class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    private Long id;
    private String name;
    private Enum<Role> role;
    private String password;
    private Long libraryId;
    private boolean disabled;


    public Member(String name, Role role, String pw, long libraryId) {
        this.name = name;
        this.role = role;
        this.password = pw;
        this.libraryId = libraryId;
        this.disabled = false;
    }
}
