package com.vaka.daily_client.error_handling.strategy.notFound;

import com.vaka.daily_client.exception.TaskNotFoundException;

public class TaskNotFoundHandlerStrategy extends AbstractNotFoundHandlerStrategy<TaskNotFoundException> {
    @Override
    public TaskNotFoundException getNotFoundByIdAndName(Integer id, String name) {
        return new TaskNotFoundException(id, name);
    }

    @Override
    public TaskNotFoundException getNotFoundById(Integer id) {
        return new TaskNotFoundException(id);
    }

    @Override
    public TaskNotFoundException getNotFoundByName(String name) {
        return new TaskNotFoundException(name);
    }
}
