package com.vaka.daily.errorhandling.handler;

import com.vaka.daily.exception.ObjectNotFoundException;
import com.vaka.daily.exception.ScheduleNotFoundException;
import com.vaka.daily.exception.UserNotFoundException;
import com.vaka.daily.model.ResponseError;

import java.io.IOException;
import java.net.URI;

public class ScheduleErrorHandler extends AbstractDomainErrorHandler {
    @Override
    public String getNotFoundExceptionName() {
        return "ScheduleNotFoundException";
    }

    @Override
    public ObjectNotFoundException getNotFoundException(Integer id, String name) {
        if (name == null) {
            return new ScheduleNotFoundException(id);
        } else if (id == null) {
            return new ScheduleNotFoundException(name);
        } else {
            return new ScheduleNotFoundException(id, name);
        }
    }
}
