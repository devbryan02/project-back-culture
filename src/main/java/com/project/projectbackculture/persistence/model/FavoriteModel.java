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
    @ManyToOne(targetEntity = UserModel.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserModel userId;

    //Relacion de muchos a uno con lugar
    @ManyToOne(targetEntity = PlaceModel.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "place_id")
    private PlaceModel placeId;

    private LocalDate savedDate;

}
