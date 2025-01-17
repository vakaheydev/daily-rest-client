package com.vaka.daily_client.exception;

public class TaskTypeNotFoundException extends ObjectNotFoundException {
    public static final String OBJECT_NAME = "Task";

    public TaskTypeNotFoundException(Integer id, String name) {
        super(OBJECT_NAME, id, name);
    }

    public TaskTypeNotFoundException(Integer id) {
        super(OBJECT_NAME, id);
    }

    public TaskTypeNotFoundException(String name) {
        super(OBJECT_NAME, name);
    }
}
