package com.vaka.daily_client.error_handling.handlers;

import com.vaka.daily_client.error_handling.ErrorHandler;
import com.vaka.daily_client.exception.ValidationException;
import com.vaka.daily_client.model.DomainType;
import com.vaka.daily_client.model.ResponseError;

public class DataIntegrityErrorHandler implements ErrorHandler {
    @Override
    public void handleError(DomainType domainType, ResponseError responseError) {
        throw new ValidationException("Data integrity error: " + responseError.getMessage());
    }
}
