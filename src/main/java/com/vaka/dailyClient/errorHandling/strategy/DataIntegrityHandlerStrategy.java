package com.vaka.dailyClient.errorHandling.strategy;

import com.vaka.dailyClient.exception.ValidationException;
import com.vaka.dailyClient.model.ResponseError;

public class DataIntegrityHandlerStrategy implements ErrorHandlerStrategy {
    @Override
    public void handleError(ResponseError responseError) {
        throw new ValidationException("Data integrity error: " + responseError.getMessage());
    }
}
