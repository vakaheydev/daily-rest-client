package com.vaka.daily_client.error_handling.strategy;

import com.vaka.daily_client.model.ObjectNotFoundException;
import com.vaka.daily_client.model.ResponseError;

public abstract class NotFoundHandlerStrategy implements ErrorHandlerStrategy {
    private record NotFoundExceptionType(Integer id, String name) {
        public static NotFoundExceptionType resolve(String requestedId, String requestedName) {
            if (requestedId == null && requestedName == null) {
                throw new RuntimeException("Wrong response error: no requested id and name");
            }

            Integer id = (requestedId != null && !requestedId.equals("null")) ? Integer.parseInt(requestedId) : null;
            return new NotFoundExceptionType(id, requestedName);
        }

        public boolean causedById() {
            return id != null;
        }

        public boolean causedByName() {
            return name != null;
        }

        public boolean causedByBoth() {
            return causedById() && causedByName();
        }
    }

    private static NotFoundExceptionType resolveNotFoundException(ResponseError error) {
        ResponseError.ResponseDetails responseDetails = error.getDetails().orElseThrow();

        String requestedId = responseDetails.find("requestedId").orElseThrow();
        String requestedName = responseDetails.find("requestedName").orElseThrow();

        return NotFoundExceptionType.resolve(requestedId, requestedName);
    }

    public abstract ObjectNotFoundException getNotFoundException(Integer id, String name);

    public abstract ObjectNotFoundException getNotFoundByIdException(Integer id);

    public abstract ObjectNotFoundException getNotFoundByNameException(String name);

    @Override
    public void handleError(ResponseError responseError) {
        var notFoundType = resolveNotFoundException(responseError);

        if (notFoundType.causedByBoth()) {
            throw getNotFoundException(notFoundType.id, notFoundType.name);
        } else if (notFoundType.causedById()) {
            throw getNotFoundByIdException(notFoundType.id);
        } else if (notFoundType.causedByName()) {
            throw getNotFoundByNameException(notFoundType.name);
        } else {
            throw new RuntimeException("Wrong response error: no requested id and name");
        }
    }
}
