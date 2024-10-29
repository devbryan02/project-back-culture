package com.project.projectbackculture.persistence.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "permission")
public class PermissionModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer permissionId;
    private String name;

}
