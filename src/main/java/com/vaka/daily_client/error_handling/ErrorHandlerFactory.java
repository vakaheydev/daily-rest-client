package com.vaka.daily_client.error_handling;

import com.vaka.daily_client.error_handling.handlers.*;
import com.vaka.daily_client.error_handling.handlers.notFound.NotFoundErrorHandler;
import com.vaka.daily_client.model.DomainType;
import com.vaka.daily_client.model.ResponseError;

import java.util.regex.Pattern;

public class ErrorHandlerFactory {
    private static final Pattern exceptionNotFoundPattern = Pattern.compile("[a-zA-Z]*NotFoundException");

    public ErrorHandler getErrorHandler(DomainType domainType, ResponseError error) {
        String errorType = error.getError();

        if (isNotFoundException(errorType)) {
            return new NotFoundErrorHandler();
        }

        return switch (errorType) {
            case "DataIntegrityViolationException" -> new DataIntegrityErrorHandler();
            case "ValidationException" -> new ValidationErrorHandler();
            case "HttpMessageNotReadableException" -> new HttpMessageNotReadableErrorHandler();
            case "NoResourceFoundException" -> new NoResourceFoundErrorHandler();
            case "DuplicateEntityException" -> new DuplicateEntityErrorHandler();
            default -> throw new NoStrategyErrorHandlingException(errorType);
        };
    }

    private boolean isNotFoundException(String errorType) {
        return exceptionNotFoundPattern.matcher(errorType).matches();
    }
}
