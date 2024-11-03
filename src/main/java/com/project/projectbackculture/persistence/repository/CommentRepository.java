package com.project.projectbackculture.persistence.repository;

import com.project.projectbackculture.persistence.model.CommentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentModel, Integer> {
}
