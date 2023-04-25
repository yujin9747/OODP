package com.example.demo.domain;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("Professor")
public class Professor extends Member{

}
