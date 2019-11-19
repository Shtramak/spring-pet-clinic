package com.shtramak.springpetclinic.service;

import java.util.Optional;
import java.util.Set;

public interface CrudService<T, ID> {
    Set<T> findAll();

    Optional<T> findById(ID id);

    T save(T object);

    void delete(T object);

    void deleteById(ID id);
}
