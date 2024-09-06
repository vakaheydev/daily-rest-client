package com.vaka.dailyClient.model;

public class TaskNotFoundException extends ObjectNotFoundException {
    public static final String OBJECT_NAME = "Task";

    public TaskNotFoundException(Integer id, String name) {
        super(OBJECT_NAME, id, name);
    }

    public TaskNotFoundException(Integer id) {
        super(OBJECT_NAME, id);
    }

    public TaskNotFoundException(String name) {
        super(OBJECT_NAME, name);
    }
}
