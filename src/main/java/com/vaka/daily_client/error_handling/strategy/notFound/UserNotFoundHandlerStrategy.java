package com.vaka.daily_client.error_handling.strategy.notFound;

import com.vaka.daily_client.error_handling.strategy.NotFoundHandlerStrategy;
import com.vaka.daily_client.exception.ObjectNotFoundException;
import com.vaka.daily_client.exception.UserNotFoundException;

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
