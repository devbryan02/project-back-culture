package com.project.projectbackculture.persistence.repository;

import com.project.projectbackculture.persistence.model.CommentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentModel, Integer> {
}
