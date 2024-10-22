package com.vaka.daily_client.error_handling.strategy;

import com.vaka.daily_client.model.ResponseError;

public interface ErrorHandlerStrategy {
    void handleError(ResponseError responseError);
}
