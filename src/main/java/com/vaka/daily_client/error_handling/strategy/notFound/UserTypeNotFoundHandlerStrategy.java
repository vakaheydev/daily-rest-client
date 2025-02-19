package com.vaka.daily_client.error_handling.strategy.notFound;

import com.vaka.daily_client.exception.notfound.UserTypeNotFoundException;

public class UserTypeNotFoundHandlerStrategy extends AbstractNotFoundHandlerStrategy<UserTypeNotFoundException> {
    @Override
    public UserTypeNotFoundException getNotFoundById(Integer id) {
        return new UserTypeNotFoundException("id", id);
    }

    @Override
    public UserTypeNotFoundException getNotFoundByName(String name) {
        return new UserTypeNotFoundException("name", name);
    }
}
