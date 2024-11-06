package com.project.projectbackculture.service.interfaces;

import java.util.List;
import java.util.Optional;

public interface ServiceGeneral<T, D, ID> {

    T save(D request);
    T update(D request, ID id);
    void delete(ID id);
    List<T> findAll();
    Optional<T> findById(ID id);

}
