package com.project.projectbackculture.persistence.repository;

import com.project.projectbackculture.persistence.model.QualificationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualificationRepository extends JpaRepository<QualificationModel, Integer> {

    boolean existsByUserUserIdAndPlacePlaceId(Integer userId, Integer placeId);
}
