package com.vaka.daily_client.error_handling.strategy;

import com.vaka.daily_client.exception.IncorrectHttpRequestException;
import com.vaka.daily_client.model.ResponseError;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpMessageNotReadableHandlerStrategy implements ErrorHandlerStrategy {
    @Override
    public void handleError(ResponseError responseError) {
        log.error("Incorrect HTTP request: {}", responseError.getMessage());
        throw new IncorrectHttpRequestException(responseError.getMessage());
    }
}
