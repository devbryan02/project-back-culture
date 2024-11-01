package com.project.projectbackculture.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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


    //Relaacion de muchos a muchos entre place y category
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "place_category",
            joinColumns = @JoinColumn(name = "place_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<CategoryModel> categories = new HashSet<>();

    //Relacion de uno a muchos con Comentario model
    @OneToMany(
            targetEntity = CommentModel.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "placeId"
    )
    @Builder.Default
    private List<CommentModel> comments = new ArrayList<>();

    //Relacion de uno a muchos con favorito
    @OneToMany(
            targetEntity = FavorityModel.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "placeId"
    )
    @Builder.Default
    private List<FavorityModel> favorities = new ArrayList<>();

    //Relacion de uno a muchos con fotos
    @OneToMany(
            targetEntity = PhotoModel.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "placeId"
    )
    @Builder.Default
    private List<PhotoModel> photos = new ArrayList<>();

    //Relacion de uno a muchos con calificacion
    @OneToMany(
            targetEntity = QualificationModel.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "placeId"
    )
    List<QualificationModel> qualifications = new ArrayList<>();


}
