package org.example.model;

import java.util.List;

public interface Repository <T>{
    T create(T entity);
    T getById(int id);
    List<T> getAll();
    boolean update(T entity);
    boolean delete(T entity);
}
