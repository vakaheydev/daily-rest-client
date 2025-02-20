package com.vaka.daily_client.error_handling.handlers.notFound;

import com.vaka.daily_client.exception.notfound.ObjectNotFoundException;
import com.vaka.daily_client.model.ResponseError;

public abstract class AbstractNotFoundHandlerStrategy<T extends ObjectNotFoundException>
        extends NotFoundHandlerStrategy {
    @Override
    public void throwNotFoundException(ResponseError.ResponseDetails responseDetails) {
        if (responseDetails.contains("requestedId")) {
            Integer id = getIntValueFromResponse(responseDetails, "requestedId");
            throw getNotFoundById(id);
        } else if (responseDetails.contains("requestedName")) {
            String name = getStringValueFromResponse(responseDetails, "requestedName");
            throw getNotFoundByName(name);
        }
    }

    public abstract T getNotFoundById(Integer id);

    public abstract T getNotFoundByName(String name);
}
