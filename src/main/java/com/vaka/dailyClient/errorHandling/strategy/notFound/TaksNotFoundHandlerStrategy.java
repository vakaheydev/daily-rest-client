package com.vaka.dailyClient.errorHandling.strategy.notFound;

import com.vaka.dailyClient.errorHandling.strategy.NotFoundHandlerStrategy;
import com.vaka.dailyClient.model.ObjectNotFoundException;
import com.vaka.dailyClient.model.TaskNotFoundException;

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
