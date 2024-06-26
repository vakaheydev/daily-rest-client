package com.vaka.daily.errorhandling.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaka.daily.model.DomainType;
import com.vaka.daily.model.ResponseError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

@Slf4j
public class MyResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return !response.getStatusCode().is2xxSuccessful();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        log.error("An error occurred in RestClient request, response: {} : {}", response.getStatusText(),
                response.getStatusCode());
    }

    @Override
    public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {
        DomainType domainType = DomainType.fromUrl(url).orElseThrow();

        InputStream responseBody = response.getBody();
        String body = new BufferedReader(new InputStreamReader(responseBody)).readLine();

        ObjectMapper objectMapper = new ObjectMapper();
        ResponseError error = objectMapper.readValue(body, ResponseError.class);

        log.error("Response ERROR: {}", error.toString());

        DomainErrorHandler domainErrorHandler = DomainErrorHandlerFactory.getErrorHandler(domainType);
        domainErrorHandler.handleError(error, url);
    }
}
