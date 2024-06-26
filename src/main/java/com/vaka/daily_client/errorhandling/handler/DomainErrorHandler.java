package com.vaka.daily_client.errorhandling.handler;

import com.vaka.daily_client.model.ResponseError;

import java.io.IOException;
import java.net.URI;

public interface DomainErrorHandler {
    void handleError(ResponseError error, URI url) throws IOException;
}
