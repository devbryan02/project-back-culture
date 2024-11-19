package com.project.projectbackculture.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "image")
public class ImageModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imageId;

    @ManyToOne(targetEntity = PlaceModel.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "place_id")
    private PlaceModel place;

    private String description;
    private String publicId;
    private String secureUrl;
    private LocalDate uploadDate;

}
