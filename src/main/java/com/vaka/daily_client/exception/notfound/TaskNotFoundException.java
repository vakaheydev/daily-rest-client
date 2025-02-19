package com.vaka.daily_client.exception.notfound;

public class TaskNotFoundException extends ObjectNotFoundException {
    public TaskNotFoundException(String detailName, Object detailValue) {
        super(detailName, detailValue);
    }

    @Override
    protected String getObjectName() {
        return "Task";
    }
}
