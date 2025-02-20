package com.vaka.daily_client.error_handling;

import com.vaka.daily_client.exception.DuplicateEntityException;
import com.vaka.daily_client.model.DomainType;
import com.vaka.daily_client.model.ResponseError;

public class DuplicateEntityErrorHandler implements ErrorHandler {
    @Override
    public void handleError(DomainType domainType, ResponseError responseError) {
        throw new DuplicateEntityException(domainType.toString().toLowerCase(), responseError.getMessage());
    }
}
