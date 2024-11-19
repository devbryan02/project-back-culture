package com.project.projectbackculture.persistence.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "category")
public class CategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    private String categoryName;
    private String description;

    //Relacion inversa entre el catergory y place
    @ManyToMany(mappedBy = "categories")
    private Set<PlaceModel> places = new HashSet<>();
}
