package com.project.projectbackculture.persistence.repository;

import com.project.projectbackculture.persistence.model.FavoriteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteModel, Integer> {
}
