package com.vaka.daily_client.errorHandling.strategy.notFound;

import com.vaka.daily_client.errorHandling.strategy.NotFoundHandlerStrategy;
import com.vaka.daily_client.model.ObjectNotFoundException;
import com.vaka.daily_client.model.UserNotFoundException;

public class UserNotFoundHandlerStrategy extends NotFoundHandlerStrategy {
    @Override
    public ObjectNotFoundException getNotFoundException(Integer id, String name) {
        return new UserNotFoundException(id, name);
    }

    @Override
    public ObjectNotFoundException getNotFoundByIdException(Integer id) {
        return new UserNotFoundException(id);
    }

    @Override
    public ObjectNotFoundException getNotFoundByNameException(String name) {
        return new UserNotFoundException(name);
    }
}
