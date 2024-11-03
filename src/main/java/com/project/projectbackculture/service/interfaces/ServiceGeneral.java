package com.project.projectbackculture.service.interfaces;

import java.util.List;
import java.util.Optional;

public interface ServiceGeneral<T, D, ID> {

    T save(D entity);
    T update(D entity);
    void delete(ID id);
    List<T> findAll();
    Optional<T> findById(ID id);

}
