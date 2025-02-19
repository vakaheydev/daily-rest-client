package com.vaka.daily_client.error_handling.strategy.notFound;

import com.vaka.daily_client.exception.notfound.TaskTypeNotFoundException;

public class TaskTypeNotFoundHandlerStrategy extends AbstractNotFoundHandlerStrategy<TaskTypeNotFoundException> {
    @Override
    public TaskTypeNotFoundException getNotFoundById(Integer id) {
        return new TaskTypeNotFoundException("id", id);
    }

    @Override
    public TaskTypeNotFoundException getNotFoundByName(String name) {
        return new TaskTypeNotFoundException("name", name);
    }
}
