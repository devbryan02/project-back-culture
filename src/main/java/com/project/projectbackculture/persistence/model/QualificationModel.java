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
    private UserModel userId;
    private PlaceModel placeId;
    private Integer punctuation;
    private LocalDate qualificationDate;

}
