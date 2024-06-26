package com.vaka.daily.abstraction;

import java.util.List;

public interface CommonClient<T> {
    List<T> getAll();

    T getById(Integer id);

    T getByUniqueName(String uniqueName);

    T create(T entity);

    T updateById(Integer id, T entity);

    void deleteById(Integer id);

    boolean isServerAlive();
}
