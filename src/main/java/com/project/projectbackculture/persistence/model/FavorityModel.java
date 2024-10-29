package com.project.projectbackculture.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "favority")
public class FavorityModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer favorityId;
    private UserModel userId;
    private PlaceModel placeId;
    private LocalDate savedDate;

}
