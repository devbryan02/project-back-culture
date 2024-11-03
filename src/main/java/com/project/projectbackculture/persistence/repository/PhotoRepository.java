package com.project.projectbackculture.persistence.repository;

import com.project.projectbackculture.persistence.model.PhotoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<PhotoModel, Integer> {
}
