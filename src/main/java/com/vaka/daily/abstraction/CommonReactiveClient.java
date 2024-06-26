package com.vaka.daily.abstraction;

import com.vaka.daily.exception.ServerNotRespondException;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CommonReactiveClient<T> {
    Mono<List<T>> getAll();

    Mono<T> getById(Integer id);

    Mono<T> create(T entity);

    Mono<T> updateById(Integer id, T entity);

    void deleteById(Integer id);

    boolean isServerAlive();
}
