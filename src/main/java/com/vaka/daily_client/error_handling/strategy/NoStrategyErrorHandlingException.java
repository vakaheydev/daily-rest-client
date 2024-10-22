package com.vaka.daily_client.error_handling.strategy;

public class NoStrategyErrorHandlingException extends RuntimeException {
    public NoStrategyErrorHandlingException(String errorType) {
        super(String.format("No error handling strategy is applicable to this error type: %s", errorType));
    }
}
