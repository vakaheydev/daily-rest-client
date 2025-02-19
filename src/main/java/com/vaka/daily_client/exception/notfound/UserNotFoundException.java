package com.vaka.daily_client.exception.notfound;

public class UserNotFoundException extends ObjectNotFoundException {
    public UserNotFoundException(String detailName, Object detailValue) {
        super(detailName, detailValue);
    }

    @Override
    protected String getObjectName() {
        return "User";
    }
}
