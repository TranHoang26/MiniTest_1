package org.example.service;

import java.util.Optional;

public interface IService<T> {
    void save(T t);
    void delete(Long id);
    Iterable<T> findAll();
    Optional<T> findById(Long id);
}