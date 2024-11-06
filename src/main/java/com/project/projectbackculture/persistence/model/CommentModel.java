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
@Table(name = "comment")
public class CommentModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    // Relacion de muchos a uno con usuario
    @ManyToOne(targetEntity = UserModel.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    //Relacion de muchos a uno con lugar
    @ManyToOne(targetEntity = PlaceModel.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "place_id")
    private PlaceModel place;

    private String textComment;
    private LocalDate commentDate;

}
