package com.example.library.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Entity
public class User {
    @Id
    private int id;
    private String username;

    @Getter
    @Setter
    private String Password;
    private String Role;

}
// where to add  generated password