package com.vaka.daily_client.exception;

public class ServerNotRespondingException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Server doesn't responding!";

    public ServerNotRespondingException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
