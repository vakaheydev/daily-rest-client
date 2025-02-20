package com.vaka.daily_client.error_handling.handlers.notFound;

import com.vaka.daily_client.error_handling.ErrorHandler;
import com.vaka.daily_client.model.DomainType;
import com.vaka.daily_client.model.ResponseError;

public class NotFoundErrorHandler implements ErrorHandler {
    @Override
    public void handleError(DomainType domainType, ResponseError responseError) {
        NotFoundHandlerStrategyFactory factory = new NotFoundHandlerStrategyFactory();

        NotFoundHandlerStrategy strategy = factory.getNotFoundHandlerStrategy(domainType, responseError);
        strategy.handleError(responseError);
    }
}
