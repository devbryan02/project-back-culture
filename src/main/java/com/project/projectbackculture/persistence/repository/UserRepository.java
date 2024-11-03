package com.project.projectbackculture.persistence.repository;

import com.project.projectbackculture.persistence.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {


}
