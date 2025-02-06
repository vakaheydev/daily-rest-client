package com.vaka.daily_client.error_handling.strategy;

import com.vaka.daily_client.exception.NoResourceFoundException;
import com.vaka.daily_client.model.ResponseError;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NoResourceFoundHandlerStrategy implements ErrorHandlerStrategy {
    @Override
    public void handleError(ResponseError responseError) {
        String message = responseError.getMessage();
        log.error("Incorrect URL: " + message);
        throw new NoResourceFoundException(message);
    }
}
