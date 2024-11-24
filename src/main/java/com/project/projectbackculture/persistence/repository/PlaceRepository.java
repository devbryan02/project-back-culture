package com.project.projectbackculture.persistence.repository;

import com.project.projectbackculture.persistence.model.PlaceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<PlaceModel, Integer> {


    @Query("select p from PlaceModel p " +
            "left join p.qualifications q " +
            "group by p " +
            "order by avg (q.punctuation) desc")
    List<PlaceModel> findAllOrderedByPunctuation();
}
