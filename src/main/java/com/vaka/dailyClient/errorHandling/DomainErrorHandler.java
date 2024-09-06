package com.vaka.dailyClient.errorHandling;

import com.vaka.dailyClient.errorHandling.strategy.*;
import com.vaka.dailyClient.model.DomainType;
import com.vaka.dailyClient.model.ResponseError;

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

        switch (errorType) {
            case "DataIntegrityViolationException":
                return new DataIntegrityHandlerStrategy();
            case "ValidationException":
                return new ValidationHandlerStrategy();
        }

        throw new NoStrategyErrorHandlingException(errorType);
    }

    private boolean isNotFoundException(String errorType) {
        return exceptionNotFoundPattern.matcher(errorType).matches();
    }
}
