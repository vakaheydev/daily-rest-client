package com.vaka.daily_client.error_handling;

import com.vaka.daily_client.model.DomainType;
import com.vaka.daily_client.model.ResponseError;

public interface ErrorHandler {
    void handleError(DomainType domainType, ResponseError responseError);
}
