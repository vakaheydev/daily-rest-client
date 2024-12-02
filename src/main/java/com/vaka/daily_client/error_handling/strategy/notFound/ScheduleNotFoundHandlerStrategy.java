package com.vaka.daily_client.error_handling.strategy.notFound;

import com.vaka.daily_client.error_handling.strategy.NotFoundHandlerStrategy;
import com.vaka.daily_client.exception.ScheduleNotFoundException;
import com.vaka.daily_client.model.ResponseError;

public class ScheduleNotFoundHandlerStrategy extends NotFoundHandlerStrategy {
    @Override
    public void throwNotFoundException(ResponseError.ResponseDetails responseDetails) {
        if (responseDetails.contains("requestedId") && responseDetails.contains("requestedName")) {
            Integer id = getIntValueFromResponse(responseDetails, "requestedId");
            String name = getStringValueFromResponse(responseDetails, "requestedName");
            throw new ScheduleNotFoundException(id, name);
        } else if (responseDetails.contains("requestedId")) {
            Integer id = getIntValueFromResponse(responseDetails, "requestedId");
            throw new ScheduleNotFoundException(id);
        } else if (responseDetails.contains("requestedName")) {
            String name = getStringValueFromResponse(responseDetails, "requestedName");
            throw new ScheduleNotFoundException(name);
        }
    }
}
