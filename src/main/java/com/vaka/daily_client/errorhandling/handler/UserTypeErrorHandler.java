package com.vaka.daily_client.errorhandling.handler;

import com.vaka.daily_client.exception.ObjectNotFoundException;
import com.vaka.daily_client.exception.UserTypeNotFoundException;

public class UserTypeErrorHandler extends AbstractDomainErrorHandler {
    @Override
    public String getNotFoundExceptionName() {
        return "UserTypeNotFoundException";
    }

    @Override
    public ObjectNotFoundException getNotFoundException(Integer id, String name) {
        if (name == null) {
            return new UserTypeNotFoundException(id);
        } else if (id == null) {
            return new UserTypeNotFoundException(name);
        } else {
            return new UserTypeNotFoundException(id, name);
        }
    }
}
