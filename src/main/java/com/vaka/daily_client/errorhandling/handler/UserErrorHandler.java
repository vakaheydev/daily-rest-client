package com.vaka.daily_client.errorhandling.handler;

import com.vaka.daily_client.exception.ObjectNotFoundException;
import com.vaka.daily_client.exception.UserNotFoundException;

public class UserErrorHandler extends AbstractDomainErrorHandler {
    @Override
    public String getNotFoundExceptionName() {
        return "UserNotFoundException";
    }

    @Override
    public ObjectNotFoundException getNotFoundException(Integer id, String name) {
        if (name == null) {
            return new UserNotFoundException(id);
        } else if (id == null) {
            return new UserNotFoundException(name);
        } else {
            return new UserNotFoundException(id, name);
        }
    }
}
