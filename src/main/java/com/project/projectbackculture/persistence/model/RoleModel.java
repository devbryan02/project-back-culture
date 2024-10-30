package com.project.projectbackculture.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    @Builder.Default
    private Set<PermissionModel> permissions = new HashSet<>();

}


