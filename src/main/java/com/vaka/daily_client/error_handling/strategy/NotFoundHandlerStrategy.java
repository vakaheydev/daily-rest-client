package com.vaka.daily_client.error_handling.strategy;

import com.vaka.daily_client.model.ResponseError;

import static com.vaka.daily_client.model.ResponseError.ResponseDetails;

public abstract class NotFoundHandlerStrategy implements ErrorHandlerStrategy {
    @Override
    public void handleError(ResponseError responseError) {
        var details = responseError.getDetails()
                .orElseThrow(() -> new IllegalStateException("Wrong answer from server: not found without details"));
        throwNotFoundException(details);
    }

    public abstract void throwNotFoundException(ResponseDetails responseDetails);

    protected Integer getIntValueFromResponse(ResponseDetails responseDetails, String name) {
        return Integer.valueOf(responseDetails.find(name).orElseThrow());
    }

    protected Long getLongValueFromResponse(ResponseDetails responseDetails, String name) {
        return Long.valueOf(responseDetails.find(name).orElseThrow());
    }

    protected String getStringValueFromResponse(ResponseDetails responseDetails, String name) {
        return responseDetails.find(name).orElseThrow();
    }
}
