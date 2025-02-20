package com.vaka.daily_client.error_handling.handlers;

import com.vaka.daily_client.error_handling.ErrorHandler;
import com.vaka.daily_client.exception.NoResourceFoundException;
import com.vaka.daily_client.model.DomainType;
import com.vaka.daily_client.model.ResponseError;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NoResourceFoundErrorHandler implements ErrorHandler {
    @Override
    public void handleError(DomainType domainType, ResponseError responseError) {
        String message = responseError.getMessage();
        log.error("Incorrect URL: " + message);
        throw new NoResourceFoundException(message);
    }
}
