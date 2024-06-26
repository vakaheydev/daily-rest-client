package com.vaka.daily.abstraction;

import com.vaka.daily.exception.ServerNotRespondException;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;

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
