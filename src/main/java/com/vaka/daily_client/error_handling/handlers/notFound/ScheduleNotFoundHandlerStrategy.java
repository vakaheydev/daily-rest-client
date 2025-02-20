package com.vaka.daily_client.error_handling.handlers.notFound;

import com.vaka.daily_client.exception.notfound.ScheduleNotFoundException;

public class ScheduleNotFoundHandlerStrategy extends AbstractNotFoundHandlerStrategy<ScheduleNotFoundException> {
    @Override
    public ScheduleNotFoundException getNotFoundById(Integer id) {
        return new ScheduleNotFoundException("id", id);
    }

    @Override
    public ScheduleNotFoundException getNotFoundByName(String name) {
        return new ScheduleNotFoundException("name", name);
    }
}
