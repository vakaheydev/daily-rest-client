package com.vaka.daily_client.error_handling.strategy.notFound;

import com.vaka.daily_client.error_handling.strategy.NotFoundHandlerStrategy;
import com.vaka.daily_client.exception.ObjectNotFoundException;
import com.vaka.daily_client.exception.ScheduleNotFoundException;

public class ScheduleNotFoundHandlerStrategy extends NotFoundHandlerStrategy {
    @Override
    public ObjectNotFoundException getNotFoundException(Integer id, String name) {
        return new ScheduleNotFoundException(id, name);
    }

    @Override
    public ObjectNotFoundException getNotFoundByIdException(Integer id) {
        return new ScheduleNotFoundException(id);
    }

    @Override
    public ObjectNotFoundException getNotFoundByNameException(String name) {
        return new ScheduleNotFoundException(name);
    }
}
