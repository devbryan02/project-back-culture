package com.project.projectbackculture.persistence.repository;

import com.project.projectbackculture.persistence.model.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageModel, Integer> {
}
