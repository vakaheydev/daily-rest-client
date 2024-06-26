package com.vaka.daily.errorhandling.handler;

import com.vaka.daily.exception.ObjectNotFoundException;
import com.vaka.daily.exception.TaskNotFoundException;

public class TaskErrorHandler extends AbstractDomainErrorHandler {
    @Override
    public String getNotFoundExceptionName() {
        return "TaskNotFoundException";
    }

    @Override
    public ObjectNotFoundException getNotFoundException(Integer id, String name) {
        if (name == null) {
            return new TaskNotFoundException(id);
        } else if (id == null) {
            return new TaskNotFoundException(name);
        } else {
            return new TaskNotFoundException(id, name);
        }
    }
}
