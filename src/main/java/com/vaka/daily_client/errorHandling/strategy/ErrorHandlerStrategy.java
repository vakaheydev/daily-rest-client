package com.vaka.daily_client.errorHandling.strategy;

import com.vaka.daily_client.model.ResponseError;

public interface ErrorHandlerStrategy {
    void handleError(ResponseError responseError);
}
