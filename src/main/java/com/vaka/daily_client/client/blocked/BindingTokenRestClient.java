package com.vaka.daily_client.client.blocked;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.vaka.daily_client.exception.ServerNotRespondingException;
import com.vaka.daily_client.model.BindingToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class BindingTokenRestClient implements BindingTokenClient {
    @Value("${app.connection.url}")
    protected String URL;
    private final ObjectMapper objectMapper;
    private final RestClient restClient;
    private final String DOMAIN_URL = "/api/binding_token";

    @Autowired
    public BindingTokenRestClient(ObjectMapper objectMapper, RestClient restClient) {
        this.objectMapper = objectMapper;
        this.restClient = restClient;
    }

    @Override
    public BindingToken getByTokenValue(String tokenValue) {
        String response;

        try {
            response = restClient.get()
                    .uri(URL + DOMAIN_URL + "/search?val={id}", tokenValue)
                    .retrieve()
                    .body(String.class);
        } catch (ResourceAccessException ignored) {
            throw new ServerNotRespondingException();
        }

        try {
            return objectMapper.readValue(response, BindingToken.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<BindingToken> getAll() {
        String response;
        try {
            response = restClient.get()
                    .uri(URL + DOMAIN_URL)
                    .retrieve()
                    .body(String.class);
        } catch (ResourceAccessException ignored) {
            throw new ServerNotRespondingException();
        }

        try {
            return objectMapper.readValue(response,
                    TypeFactory.defaultInstance().constructCollectionType(List.class, BindingToken.class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BindingToken getById(Integer id) {
        String response;

        try {
            response = restClient.get()
                    .uri(URL + DOMAIN_URL + "/{id}", id)
                    .retrieve()
                    .body(String.class);
        } catch (ResourceAccessException ignored) {
            throw new ServerNotRespondingException();
        }

        try {
            return objectMapper.readValue(response, BindingToken.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BindingToken getByUserId(Integer userId) {
        String response;

        try {
            response = restClient.get()
                    .uri(URL + DOMAIN_URL + "/user/{id}", userId)
                    .retrieve()
                    .body(String.class);
        } catch (ResourceAccessException ignored) {
            throw new ServerNotRespondingException();
        }

        try {
            return objectMapper.readValue(response, BindingToken.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BindingToken createToken(Integer userId) {
        String response;
        try {
            response = restClient.post()
                    .uri(URL + DOMAIN_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(userId)
                    .retrieve()
                    .body(String.class);
        } catch (ResourceAccessException ignored) {
            throw new ServerNotRespondingException();
        }

        try {
            return objectMapper.readValue(response, BindingToken.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
