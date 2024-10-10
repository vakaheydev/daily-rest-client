package com.vaka.daily_client.errorHandling.strategy.notFound;

import com.vaka.daily_client.errorHandling.strategy.NotFoundHandlerStrategy;
import com.vaka.daily_client.model.ObjectNotFoundException;
import com.vaka.daily_client.model.UserTypeNotFoundException;

public class UserTypeNotFoundHandlerStrategy extends NotFoundHandlerStrategy {
    @Override
    public ObjectNotFoundException getNotFoundException(Integer id, String name) {
        return new UserTypeNotFoundException(id, name);
    }

    @Override
    public ObjectNotFoundException getNotFoundByIdException(Integer id) {
        return new UserTypeNotFoundException(id);
    }

    @Override
    public ObjectNotFoundException getNotFoundByNameException(String name) {
        return new UserTypeNotFoundException(name);
    }
}
