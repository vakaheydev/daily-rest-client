package com.vaka.daily_client.error_handling.strategy.notFound;

import com.vaka.daily_client.error_handling.strategy.NotFoundHandlerStrategy;
import com.vaka.daily_client.exception.notfound.UserNotFoundException;
import com.vaka.daily_client.model.ResponseError;

public class UserNotFoundHandlerStrategy extends NotFoundHandlerStrategy {
    @Override
    public void throwNotFoundException(ResponseError.ResponseDetails responseDetails) {
        if (responseDetails.contains("requestedTelegramId")) {
            Long telegramId = getLongValueFromResponse(responseDetails, "requestedTelegramId");
            throw new UserNotFoundException("telegramId", telegramId);
        } else if (responseDetails.contains("requestedId")) {
            Integer id = getIntValueFromResponse(responseDetails, "requestedId");
            throw new UserNotFoundException("id", id);
        } else if (responseDetails.contains("requestedName")) {
            String name = getStringValueFromResponse(responseDetails, "requestedName");
            throw new UserNotFoundException("name", name);
        }
    }
}
