package com.vaka.daily.errorhandling.handler;

import com.vaka.daily.exception.UserNotFoundException;
import com.vaka.daily.model.ResponseError;

import java.io.IOException;
import java.net.URI;

public class UserTypeErrorHandler implements DomainErrorHandler {
    @Override
    public void handleError(ResponseError error, URI url) throws IOException {
        if (error.getError().equals("UserTypeNotFoundException")) {
            DomainErrorHandlerUtils.NotFoundExceptionType type =
                    DomainErrorHandlerUtils.resolveNotFoundException(error);
            if (type.causedById()) {
                throw new UserNotFoundException(type.getId());
            } else {
                throw new UserNotFoundException(type.value());
            }
        }
    }
}
