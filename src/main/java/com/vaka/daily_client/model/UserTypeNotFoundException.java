package com.vaka.daily_client.model;

public class UserTypeNotFoundException extends ObjectNotFoundException {
    public static final String OBJECT_NAME = "User Type";

    public UserTypeNotFoundException(Integer id, String name) {
        super(OBJECT_NAME, id, name);
    }

    public UserTypeNotFoundException(Integer id) {
        super(OBJECT_NAME, id);
    }

    public UserTypeNotFoundException(String name) {
        super(OBJECT_NAME, name);
    }
}
