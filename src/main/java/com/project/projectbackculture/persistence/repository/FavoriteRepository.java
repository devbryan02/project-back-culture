package com.project.projectbackculture.persistence.repository;

import com.project.projectbackculture.persistence.model.FavoriteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<FavoriteModel, Integer> {
}
