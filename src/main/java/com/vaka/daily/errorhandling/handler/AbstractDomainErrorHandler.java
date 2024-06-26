package com.vaka.daily.errorhandling.handler;

import com.vaka.daily.exception.ObjectNotFoundException;
import com.vaka.daily.exception.ValidationException;
import com.vaka.daily.model.ResponseError;

import java.io.IOException;
import java.net.URI;

public abstract class AbstractDomainErrorHandler implements DomainErrorHandler {
    @Override
    public void handleError(ResponseError error, URI url) throws IOException {
        String errorType = error.getError();

        if (errorType.equals(getNotFoundExceptionName())) {
            handleNotFoundException(error, url);
        } else if (errorType.equals("DataIntegrityViolationException")) {
            handleDataIntegrityException(error, url);
        } else if (errorType.equals("ValidationException")) {
            handleValidationException(error, url);
        }
    }

    public void handleNotFoundException(ResponseError error, URI url) {
        DomainErrorHandlerUtils.NotFoundExceptionType type =
                DomainErrorHandlerUtils.resolveNotFoundException(error);
        if (type.causedById()) {
            throw getNotFoundException(type.getId(), null);
        } else {
            throw getNotFoundException(null, type.value());
        }
    }

    public abstract String getNotFoundExceptionName();

    public abstract ObjectNotFoundException getNotFoundException(Integer id, String name);

    static void handleValidationException(ResponseError error, URI url) {
        throw new ValidationException(error.getMessage());
    }

    static void handleDataIntegrityException(ResponseError error, URI url) {
        throw new ValidationException("Data integrity error: " + error.getMessage());
    }
}
