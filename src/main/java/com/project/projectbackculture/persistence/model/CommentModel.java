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
    private UserModel userId;
    private PlaceModel placeId;
    private String textComment;
    private LocalDate commentDate;

}
