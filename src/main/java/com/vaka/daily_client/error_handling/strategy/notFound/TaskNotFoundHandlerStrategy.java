package com.vaka.daily_client.error_handling.strategy.notFound;

import com.vaka.daily_client.exception.notfound.TaskNotFoundException;

public class TaskNotFoundHandlerStrategy extends AbstractNotFoundHandlerStrategy<TaskNotFoundException> {
    @Override
    public TaskNotFoundException getNotFoundById(Integer id) {
        return new TaskNotFoundException("id", id);
    }

    @Override
    public TaskNotFoundException getNotFoundByName(String name) {
        return new TaskNotFoundException("name", name);
    }
}
