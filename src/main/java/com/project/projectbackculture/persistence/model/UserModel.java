package com.project.projectbackculture.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class UserModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String fullName;
    @Column(unique = true)
    private String email;
    private String password;
    private LocalDate registrationDate;

    // Atributos necesarios para spring security
    private boolean isEnable;
    private boolean accountNoExpired;
    private boolean accountNoLocked;
    private boolean credentialNoExpired;


}
