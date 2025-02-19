package com.vaka.daily_client.error_handling.strategy.notFound;

import com.vaka.daily_client.error_handling.strategy.NotFoundHandlerStrategy;
import com.vaka.daily_client.exception.UserNotFoundException;
import com.vaka.daily_client.model.ResponseError;

public class UserNotFoundHandlerStrategy extends NotFoundHandlerStrategy {
    @Override
    public void throwNotFoundException(ResponseError.ResponseDetails responseDetails) {
        if (responseDetails.contains("requestedTelegramId")) {
            Long tgId = getLongValueFromResponse(responseDetails, "requestedTelegramId");
            throw new UserNotFoundException(tgId);
        }

        if (responseDetails.contains("requestedId") && responseDetails.contains("requestedName")) {
            Integer id = getIntValueFromResponse(responseDetails, "requestedId");
            String name = getStringValueFromResponse(responseDetails, "requestedName");
            throw new UserNotFoundException(id, name);
        } else if (responseDetails.contains("requestedId")) {
            Integer id = getIntValueFromResponse(responseDetails, "requestedId");
            throw new UserNotFoundException(id);
        } else if (responseDetails.contains("requestedName")) {
            String name = getStringValueFromResponse(responseDetails, "requestedName");
            throw new UserNotFoundException(name);
        }
    }
}
