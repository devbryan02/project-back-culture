package com.project.projectbackculture.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "photo")
public class PhotoModel {

    private Integer photoId;
    private PlaceModel placeId;
    private String pathPhoto;
    private String description;
    private LocalDateTime uploadDate;

}
