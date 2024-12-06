package com.project.projectbackculture.persistence.repository;

import com.project.projectbackculture.persistence.model.PlaceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<PlaceModel, Integer> {


    // Consultar datos de los lugares mas populares de forma desc
    @Query("SELECT p FROM PlaceModel p " +
            "LEFT JOIN p.qualifications q " +
            "GROUP BY p " +
            "ORDER BY AVG(q.punctuation) desc")
    List<PlaceModel> findAllOrderedByPunctuation();

}
