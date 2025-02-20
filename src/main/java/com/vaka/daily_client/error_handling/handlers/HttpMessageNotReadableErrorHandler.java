package com.vaka.daily_client.error_handling.handlers;

import com.vaka.daily_client.error_handling.ErrorHandler;
import com.vaka.daily_client.exception.IncorrectHttpRequestException;
import com.vaka.daily_client.model.DomainType;
import com.vaka.daily_client.model.ResponseError;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpMessageNotReadableErrorHandler implements ErrorHandler {
    @Override
    public void handleError(DomainType domainType, ResponseError responseError) {
        log.error("Incorrect HTTP request: {}", responseError.getMessage());
        throw new IncorrectHttpRequestException(responseError.getMessage());
    }
}
