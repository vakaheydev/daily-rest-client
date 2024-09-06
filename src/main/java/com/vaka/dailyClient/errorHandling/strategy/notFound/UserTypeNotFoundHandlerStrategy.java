package com.vaka.dailyClient.errorHandling.strategy.notFound;

import com.vaka.dailyClient.errorHandling.strategy.NotFoundHandlerStrategy;
import com.vaka.dailyClient.model.ObjectNotFoundException;
import com.vaka.dailyClient.model.UserTypeNotFoundException;

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
