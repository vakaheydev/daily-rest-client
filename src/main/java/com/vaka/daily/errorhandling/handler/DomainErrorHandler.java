package com.vaka.daily.errorhandling.handler;

import com.vaka.daily.model.ResponseError;

import java.io.IOException;
import java.net.URI;

public interface DomainErrorHandler {
    void handleError(ResponseError error, URI url) throws IOException;
}
