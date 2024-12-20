package com.project.projectbackculture.persistence.repository;

import com.project.projectbackculture.persistence.model.PlaceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    //BUscar lugares por palabra
    @Query("SELECT p FROM PlaceModel p " +
            "WHERE LOWER(p.name) LIKE LOWER(CONCAT(:palabra, '%')) " +
            "OR LOWER(p.description) LIKE LOWER(CONCAT(:palabra, '%')) " +
            "OR LOWER(p.location) LIKE LOWER(CONCAT(:palabra, '%'))" )
    List<PlaceModel> searchByKerword(@Param("palabra") String palabra);

    // Listar lugares por categoria
    @Query("SELECT p FROM PlaceModel p " +
            "INNER JOIN p.categories c " +
            "WHERE LOWER(c.categoryName) = LOWER(:category) " +
            "GROUP BY p " +
            "ORDER BY  p.placeId")
    List<PlaceModel> findPlaceByCategory(String category);

    @Query("SELECT p FROM PlaceModel p " +
            "INNER JOIN p.categories c " +
            "WHERE LOWER(c.categoryName) = LOWER(:category) " +
            "AND LOWER(p.province) = LOWER(:province) " +
            "GROUP BY p " +
            "ORDER BY  p.placeId")
    List<PlaceModel> findPlaceModelByCategoryAndProvince(String category, String province);
}



