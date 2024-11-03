package com.project.projectbackculture.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "users")
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

    //Relacion de uno a muchos con Comentario Model
    @OneToMany(
            targetEntity = CommentModel.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "userId"
    )
    @Builder.Default
    private List<CommentModel> comments = new ArrayList<>();

    // Relacion de uno a muchos con favorito
    @OneToMany(
            targetEntity = FavoriteModel.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "userId"
    )
    @Builder.Default
    private List<FavoriteModel> favorities = new ArrayList<>();

    // Relacion de uno a muchos con calificacion
    @OneToMany(
            targetEntity = QualificationModel.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "userId"
    )
    @Builder.Default
    private List<QualificationModel> qualifications = new ArrayList<>();

    // Relacion de muchos a muchos con roles
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @Builder.Default
    private Set<RoleModel> roles = new HashSet<>();

}
