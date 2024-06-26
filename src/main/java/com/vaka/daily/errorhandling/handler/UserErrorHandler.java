package com.vaka.daily.errorhandling.handler;

import com.vaka.daily.exception.UserNotFoundException;
import com.vaka.daily.exception.ValidationException;
import com.vaka.daily.model.ResponseError;

import java.io.IOException;
import java.net.URI;

public class UserErrorHandler implements DomainErrorHandler {
    @Override
    public void handleError(ResponseError error, URI url) throws IOException {
        switch (error.getError()) {
            case "UserNotFoundException" -> {
                DomainErrorHandlerUtils.NotFoundExceptionType type =
                        DomainErrorHandlerUtils.resolveNotFoundException(error);
                if (type.causedById()) {
                    throw new UserNotFoundException(type.getId());
                } else {
                    throw new UserNotFoundException(type.value());
                }
            }

            case "DataIntegrityViolationException" -> {
                throw new ValidationException("Data integrity error: " + error.getMessage());
            }
        }
    }
}
