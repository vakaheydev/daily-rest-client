package com.vaka.daily_client.error_handling.strategy;

import com.vaka.daily_client.exception.ValidationException;
import com.vaka.daily_client.model.ResponseError;

public class ValidationHandlerStrategy implements ErrorHandlerStrategy {
    @Override
    public void handleError(ResponseError responseError) {
        throw new ValidationException(responseError.getMessage());
    }
}
