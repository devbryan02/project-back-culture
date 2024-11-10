package com.project.projectbackculture.persistence.repository;

import com.project.projectbackculture.persistence.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

    Optional<UserModel> findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
