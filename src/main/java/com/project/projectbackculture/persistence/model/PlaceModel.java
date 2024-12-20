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
    private String distance;
    private String province;
    private boolean isVisible;


    //Relaacion de muchos a muchos entre place y category
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "place_category",
            joinColumns = @JoinColumn(name = "place_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @Builder.Default
    private Set<CategoryModel> categories = new HashSet<>();

    //Relacion de uno a muchos con Comentario model
    @OneToMany(
            targetEntity = CommentModel.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "place"
    )
    @Builder.Default
    private List<CommentModel> comments = new ArrayList<>();

    //Relacion de uno a muchos con favorito
    @OneToMany(
            targetEntity = FavoriteModel.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "place"
    )
    @Builder.Default
    private List<FavoriteModel> favorities = new ArrayList<>();

    //Relacion de uno a muchos con fotos
    @OneToMany(
            targetEntity = ImageModel.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "place"
    )
    @Builder.Default
    private List<ImageModel> images = new ArrayList<>();

    //Relacion de uno a muchos con calificacion
    @OneToMany(
            targetEntity = QualificationModel.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "place"
    )
    @Builder.Default
    List<QualificationModel> qualifications = new ArrayList<>();

    //Calculando la calificacion
    public double getPunctuationAverage() {

        if(qualifications.isEmpty()) return 0;

        return qualifications.stream()
                .mapToInt(QualificationModel::getPunctuation)
                .average()
                .orElse(0);
    }
}
