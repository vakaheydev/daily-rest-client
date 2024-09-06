package com.vaka.dailyClient.errorHandling.strategy;

import com.vaka.dailyClient.model.ResponseError;

public interface ErrorHandlerStrategy {
    void handleError(ResponseError responseError);
}
