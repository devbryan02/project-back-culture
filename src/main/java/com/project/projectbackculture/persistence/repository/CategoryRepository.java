package com.project.projectbackculture.persistence.repository;

import com.project.projectbackculture.persistence.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryModel, Integer> {
}
