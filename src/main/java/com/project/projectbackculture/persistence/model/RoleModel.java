package com.project.projectbackculture.persistence.model;

import jakarta.persistence.*;
import lombok.*;

// Roles XDXDXDXD
enum RoleEnum{
    USER,
    ADMIN
}

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "role")
public class RoleModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    @JoinColumn(name = "role_name")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

}


