package com.vaka.daily_client.exception;

public class IncorrectHttpRequestException extends RuntimeException {
    public IncorrectHttpRequestException(String message) {
        super(message);
    }
}
