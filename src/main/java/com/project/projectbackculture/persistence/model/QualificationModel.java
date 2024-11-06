package com.project.projectbackculture.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "qualification")
public class QualificationModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer qualificationId;

    //Relacion de muchos a uno con usuario
    @ManyToOne(targetEntity = UserModel.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserModel user;

    //Relacion de muchos a uno con lugar
    @ManyToOne(targetEntity = PlaceModel.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "place_id")
    private PlaceModel place;

    private Integer punctuation;
    private LocalDate qualificationDate;

}
