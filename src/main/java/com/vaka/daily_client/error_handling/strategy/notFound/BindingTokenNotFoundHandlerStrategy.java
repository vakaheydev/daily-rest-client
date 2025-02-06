package com.vaka.daily_client.error_handling.strategy.notFound;

import com.vaka.daily_client.error_handling.strategy.NotFoundHandlerStrategy;
import com.vaka.daily_client.exception.BindingTokenNotFoundException;
import com.vaka.daily_client.model.ResponseError;

public class BindingTokenNotFoundHandlerStrategy extends NotFoundHandlerStrategy {
    @Override
    public void throwNotFoundException(ResponseError.ResponseDetails responseDetails) {
        if (responseDetails.contains("requestedId")) {
            Integer id = getIntValueFromResponse(responseDetails, "requestedId");
            throw BindingTokenNotFoundException.byId(id);
        } else if (responseDetails.contains("requestedUser")) {
            Integer userId = getIntValueFromResponse(responseDetails, "requestedUser");
            throw BindingTokenNotFoundException.byUserId(userId);
        } else if (responseDetails.contains("requestedValue")) {
            String tokenValue = getStringValueFromResponse(responseDetails, "requestedValue");
            throw BindingTokenNotFoundException.byValue(tokenValue);
        }
    }
}
