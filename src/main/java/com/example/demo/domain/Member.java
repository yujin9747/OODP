package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    private Role role;
    private String password;

    @ManyToOne
    @JoinColumn(name = "library_id")
    private Library library;
    private boolean disabled;
    private boolean externalLibraryPermission;

    @OneToMany(mappedBy = "member")
    private List<RentalInfo> rentalInfoList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<ReservationInfo> reservationInfoList = new ArrayList<>();


    public Member(String name, Role role, String pw, Library library) {
        this.name = name;
        this.role = role;
        this.password = pw;
        this.library = library;
        this.disabled = false;
        this.externalLibraryPermission = false;
    }

    public void rentBook(RentalInfo rentalInfo){
//        this.rentalInfoList.add(rentalInfo);
        rentalInfo.setMember(this);
    }
}
