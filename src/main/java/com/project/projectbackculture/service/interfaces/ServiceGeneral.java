package com.project.projectbackculture.service.interfaces;

import java.util.List;
import java.util.Optional;

public interface ServiceGeneral<T,ID> {

    T save(T entity);
    T update(T entity);
    void delete(ID id);
    List<T> findAll();
    Optional<T> findById(ID id);

}
