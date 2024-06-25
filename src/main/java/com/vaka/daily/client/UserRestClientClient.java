package com.vaka.daily.client;

import com.vaka.daily.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
@Slf4j
public class UserRestClientClient implements UserClient {
    RestClient restClient;
    private static final String URL = "http://localhost:8080/api";

    @Autowired
    public UserRestClientClient(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public List<User> getAll() {
        return restClient.get()
                .uri(URL + "/user")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    @Override
    public User getById(Integer id) {
        return restClient.get()
                .uri(URL + "/user/{id}", id)
                .retrieve()
                .body(User.class);
    }

    @Override
    public User getByUniqueName(String uniqueName) {
        return restClient.get()
                .uri(URL + "/user/search?login={name}", uniqueName)
                .retrieve()
                .body(User.class);
    }

    @Override
    public User create(User entity) {
        return null;
    }

    @Override
    public User updateById(Integer id, User entity) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public boolean isServerAlive() {
        try {
            restClient.head()
                    .uri("http://localhost:8080/status")
                    .retrieve()
                    .body(String.class);
            return true;
        } catch (ResourceAccessException ex) {
            log.error("Server is not responding! {}", ex.getMessage());
            return false;
        }
    }
}
