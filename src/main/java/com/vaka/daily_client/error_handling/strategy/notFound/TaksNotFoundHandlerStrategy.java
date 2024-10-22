package com.vaka.daily_client.error_handling.strategy.notFound;

import com.vaka.daily_client.error_handling.strategy.NotFoundHandlerStrategy;
import com.vaka.daily_client.model.ObjectNotFoundException;
import com.vaka.daily_client.model.TaskNotFoundException;

public class TaksNotFoundHandlerStrategy extends NotFoundHandlerStrategy {
    @Override
    public ObjectNotFoundException getNotFoundException(Integer id, String name) {
        return new TaskNotFoundException(id, name);
    }

    @Override
    public ObjectNotFoundException getNotFoundByIdException(Integer id) {
        return new TaskNotFoundException(id);
    }

    @Override
    public ObjectNotFoundException getNotFoundByNameException(String name) {
        return new TaskNotFoundException(name);
    }
}
