package com.project.projectbackculture.persistence.repository;

import com.project.projectbackculture.persistence.model.CommentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentModel, Integer> {

    boolean existsByUserUserIdAndPlacePlaceId(Integer userId, Integer placeId);

    @Query("SELECT c FROM CommentModel c WHERE c.place.placeId = :placeId ")
    List<CommentModel> findCommentByPlaceId(Integer placeId);
}
