package com.vaka.daily_client.error_handling.strategy.notFound;

import com.vaka.daily_client.error_handling.strategy.NotFoundHandlerStrategy;
import com.vaka.daily_client.exception.ObjectNotFoundException;
import com.vaka.daily_client.model.ResponseError;

public abstract class AbstractNotFoundHandlerStrategy<T extends ObjectNotFoundException>
        extends NotFoundHandlerStrategy {
    @Override
    public void throwNotFoundException(ResponseError.ResponseDetails responseDetails) {
        if (responseDetails.contains("requestedId") && responseDetails.contains("requestedName")) {
            Integer id = getIntValueFromResponse(responseDetails, "requestedId");
            String name = getStringValueFromResponse(responseDetails, "requestedName");
            throw getNotFoundByIdAndName(id, name);
        } else if (responseDetails.contains("requestedId")) {
            Integer id = getIntValueFromResponse(responseDetails, "requestedId");
            throw getNotFoundById(id);
        } else if (responseDetails.contains("requestedName")) {
            String name = getStringValueFromResponse(responseDetails, "requestedName");
            throw getNotFoundByName(name);
        }
    }

    public abstract T getNotFoundByIdAndName(Integer id, String name);

    public abstract T getNotFoundById(Integer id);

    public abstract T getNotFoundByName(String name);
}
