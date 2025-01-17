package com.vaka.daily_client.error_handling.strategy.notFound;

import com.vaka.daily_client.exception.TaskTypeNotFoundException;

public class TaskTypeNotFoundHandlerStrategy extends AbstractNotFoundHandlerStrategy<TaskTypeNotFoundException> {
    @Override
    public TaskTypeNotFoundException getNotFoundByIdAndName(Integer id, String name) {
        return new TaskTypeNotFoundException(id, name);
    }

    @Override
    public TaskTypeNotFoundException getNotFoundById(Integer id) {
        return new TaskTypeNotFoundException(id);
    }

    @Override
    public TaskTypeNotFoundException getNotFoundByName(String name) {
        return new TaskTypeNotFoundException(name);
    }
}
