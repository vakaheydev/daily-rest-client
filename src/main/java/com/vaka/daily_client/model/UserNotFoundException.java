package com.vaka.daily_client.model;

public class UserNotFoundException extends ObjectNotFoundException {
    public static final String OBJECT_NAME = "User";

    public UserNotFoundException(Integer id, String name) {
        super(OBJECT_NAME, id, name);
    }

    public UserNotFoundException(Integer id) {
        super(OBJECT_NAME, id);
    }

    public UserNotFoundException(String name) {
        super(OBJECT_NAME, name);
    }
}
