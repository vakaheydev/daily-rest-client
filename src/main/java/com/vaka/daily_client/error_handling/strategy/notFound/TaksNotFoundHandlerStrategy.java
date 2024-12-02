package com.vaka.daily_client.error_handling.strategy.notFound;

import com.vaka.daily_client.error_handling.strategy.NotFoundHandlerStrategy;
import com.vaka.daily_client.exception.TaskNotFoundException;
import com.vaka.daily_client.model.ResponseError.ResponseDetails;

public class TaksNotFoundHandlerStrategy extends NotFoundHandlerStrategy {
    @Override
    public void throwNotFoundException(ResponseDetails responseDetails) {
        if (responseDetails.contains("requestedId") && responseDetails.contains("requestedName")) {
            Integer id = getIntValueFromResponse(responseDetails, "requestedId");
            String name = getStringValueFromResponse(responseDetails, "requestedName");
            throw new TaskNotFoundException(id, name);
        } else if (responseDetails.contains("requestedId")) {
            Integer id = getIntValueFromResponse(responseDetails, "requestedId");
            throw new TaskNotFoundException(id);
        } else if (responseDetails.contains("requestedName")) {
            String name = getStringValueFromResponse(responseDetails, "requestedName");
            throw new TaskNotFoundException(name);
        }
    }
}
