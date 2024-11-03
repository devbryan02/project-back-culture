package com.project.projectbackculture.persistence.repository;

import com.project.projectbackculture.persistence.model.PlaceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<PlaceModel, Integer> {
}
