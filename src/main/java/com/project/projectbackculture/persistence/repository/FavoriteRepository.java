package com.project.projectbackculture.persistence.repository;

import com.project.projectbackculture.persistence.model.FavoriteModel;
import com.project.projectbackculture.persistence.model.PlaceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteModel, Integer> {

    boolean existsByUserUserIdAndPlacePlaceId(Integer userId, Integer placeId);

    @Query("SELECT f.place FROM FavoriteModel f WHERE f.user.username = :username")
    List<PlaceModel> findPlacesByUsername(String username);
    void deleteByUserUsernameAndPlacePlaceId(String username, Integer placeId);
}
