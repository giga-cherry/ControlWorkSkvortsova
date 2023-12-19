package org.example.dao;

import java.util.List;

public interface Dao<T> {

    T get(long id);

    List<T> getAll();

    void save (T t);

    void delete(Long id);
}
