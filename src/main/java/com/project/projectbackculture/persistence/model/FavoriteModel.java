package com.project.projectbackculture.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "favority")
public class FavoriteModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer favoriteId;

    //Relacion de muchos a uno con usuario
    @ManyToOne(targetEntity = UserModel.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserModel user;

    //Relacion de muchos a uno con lugar
    @ManyToOne(targetEntity = PlaceModel.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private PlaceModel place;

    private LocalDate savedDate;

}
