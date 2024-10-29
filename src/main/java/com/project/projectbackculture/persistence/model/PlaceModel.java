package com.project.projectbackculture.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "place")
public class PlaceModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer placeId;
    private String name;
    private String description;
    private String location;
    private boolean isVisible = true; // Valor por defecto en true


    //Relaacion entre place y category
    @ManyToMany
    @JoinTable(
            name = "place_category",
            joinColumns = @JoinColumn(name = "place_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<CategoryModel> categories = new HashSet<>();

}
