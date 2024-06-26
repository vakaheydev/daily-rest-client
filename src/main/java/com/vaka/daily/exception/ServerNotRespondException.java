package com.vaka.daily.exception;

public class ServerNotRespondException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Server doesn't responding!";

    public ServerNotRespondException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
