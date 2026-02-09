package edu.aitu.oop3.components.common;

import java.util.List;

public interface BaseRepository<T, ID> {
    void save(T entity);
    T findById(ID id);
    List<T> findAll();
    void deleteById(ID id);
}