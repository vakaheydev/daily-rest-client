package com.vaka.daily_client.error_handling;

import com.vaka.daily_client.error_handling.strategy.*;
import com.vaka.daily_client.model.DomainType;
import com.vaka.daily_client.model.ResponseError;

import java.net.URI;
import java.util.regex.Pattern;

public class DomainErrorHandler {
    private static final Pattern exceptionNotFoundPattern = Pattern.compile("[a-zA-Z]*NotFoundException");

    public void handleError(ResponseError error, URI url, DomainType domainType) {
        ErrorHandlerStrategy strategy = getErrorHandlerStrategy(error, domainType);
        strategy.handleError(error);
    }

    private ErrorHandlerStrategy getErrorHandlerStrategy(ResponseError error, DomainType domainType) {
        String errorType = error.getError();
        var strategyFactory = new ErrorHandlerStrategyFactory();

        if (isNotFoundException(errorType)) {
            return strategyFactory.getNotFoundStrategy(domainType);
        }

        return switch (errorType) {
            case "DataIntegrityViolationException" -> new DataIntegrityHandlerStrategy();
            case "ValidationException" -> new ValidationHandlerStrategy();
            case "HttpMessageNotReadableException" -> new HttpMessageNotReadableHandlerStrategy();
            case "NoResourceFoundException" -> new NoResourceFoundHandlerStrategy();
            default -> throw new NoStrategyErrorHandlingException(errorType);
        };
    }

    private boolean isNotFoundException(String errorType) {
        return exceptionNotFoundPattern.matcher(errorType).matches();
    }
}
