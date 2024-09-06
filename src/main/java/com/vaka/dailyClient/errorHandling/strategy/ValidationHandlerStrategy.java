package com.vaka.dailyClient.errorHandling.strategy;

import com.vaka.dailyClient.exception.ValidationException;
import com.vaka.dailyClient.model.ResponseError;

public class ValidationHandlerStrategy implements ErrorHandlerStrategy {
    @Override
    public void handleError(ResponseError responseError) {
        throw new ValidationException(responseError.getMessage());
    }
}
