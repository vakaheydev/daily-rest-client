package com.vaka.daily_client.error_handling.strategy.notFound;

import com.vaka.daily_client.exception.ScheduleNotFoundException;

public class ScheduleNotFoundHandlerStrategy extends AbstractNotFoundHandlerStrategy<ScheduleNotFoundException> {
    @Override
    public ScheduleNotFoundException getNotFoundByIdAndName(Integer id, String name) {
        return new ScheduleNotFoundException(id, name);
    }

    @Override
    public ScheduleNotFoundException getNotFoundById(Integer id) {
        return new ScheduleNotFoundException(id);
    }

    @Override
    public ScheduleNotFoundException getNotFoundByName(String name) {
        return new ScheduleNotFoundException(name);
    }
}
