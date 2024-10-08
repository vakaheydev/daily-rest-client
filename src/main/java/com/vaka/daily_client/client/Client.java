package com.vaka.daily_client.client;

import java.util.List;

public interface Client<T> {
    List<T> getAll();

    T getById(Integer id);

    T getByUniqueName(String uniqueName);

    T create(T entity);

    T updateById(Integer id, T entity);

    void deleteById(Integer id);

    boolean isServerAlive();
}
