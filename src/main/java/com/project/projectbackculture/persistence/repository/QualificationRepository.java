package com.project.projectbackculture.persistence.repository;

import com.project.projectbackculture.persistence.model.QualificationModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QualificationRepository extends JpaRepository<QualificationModel, Integer> {
}
