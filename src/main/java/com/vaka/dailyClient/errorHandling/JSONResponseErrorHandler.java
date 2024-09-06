package com.vaka.dailyClient.errorHandling;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaka.dailyClient.model.DomainType;
import com.vaka.dailyClient.model.ResponseError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

@Component
@Slf4j
public class JSONResponseErrorHandler implements ResponseErrorHandler {
    private final ObjectMapper objectMapper;

    public JSONResponseErrorHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

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
        ResponseError responseError = getResponseError(response);

        log.error("Response error on '{}': {}", url, responseError.toString());

        DomainErrorHandler domainErrorHandler = new DomainErrorHandler();
        domainErrorHandler.handleError(responseError, url, domainType);
    }

    private ResponseError getResponseError(ClientHttpResponse response) {
        String body = getBody(response);
        try {
            return objectMapper.readValue(body, ResponseError.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Couldn't parse response to JSON: " + e.getMessage(), e);
        }
    }

    private String getBody(ClientHttpResponse response) {
        try {
            InputStream responseBody = response.getBody();
            return new BufferedReader(new InputStreamReader(responseBody)).readLine();
        } catch (IOException e) {
            throw new RuntimeException("Couldn't get body of response: " + e.getMessage(), e);
        }
    }
}
