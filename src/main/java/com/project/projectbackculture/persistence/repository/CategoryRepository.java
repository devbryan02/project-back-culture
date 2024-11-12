package com.project.projectbackculture.persistence.repository;

import com.project.projectbackculture.persistence.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, Integer> {

    // no hay mas metodos para category model
    boolean existsByCategoryName(String name);

}
