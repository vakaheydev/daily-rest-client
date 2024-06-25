package com.vaka.daily.client;

import com.vaka.daily.abstraction.CommonClient;
import com.vaka.daily.exception.UserNotFoundException;
import com.vaka.daily.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class UserRestTemplateClient implements UserClient, CommonClient<User> {
    RestTemplate restTemplate;
    private static final String URL = "http://localhost:8080/api/user";

    @Autowired
    public UserRestTemplateClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<User> getAll() {
        List<User> usersList;
        try {
            User[] users = restTemplate.getForObject(URL, User[].class);
            usersList = Arrays.stream(Objects.requireNonNull(users)).toList();
        }
        catch (HttpClientErrorException ex) {
            log.error("Error with fetching all users, responseBody: " + ex.getResponseBodyAsString());
            String responseBody = ex.getResponseBodyAsString();
            throw new RuntimeException("An error occurred while fetching all users: " + responseBody);
        }
        return usersList;
    }

    public List<User> secondGetAll() {
        RestClient restClient = RestClient.create();

        return Arrays.stream(restClient.get()
                .uri(URL)
                .retrieve()
                .body(User[].class))
                .toList();
    }

    @Override
    public User getById(Integer id) {
        try {
            return restTemplate.getForObject(URL + "/{id}", User.class, id);
        } catch (HttpClientErrorException ex) {
            log.error("Error with User {ID: " + id + "}, responseBody: " + ex.getResponseBodyAsString());

            if (ex.getStatusCode().value() == 404) {
                throw new UserNotFoundException(id);
            }
            else {
                String responseBody = ex.getResponseBodyAsString();
                throw new RuntimeException("An error occurred while fetching user with ID " + id + ": " + responseBody);
            }
        }
    }


    @Override
    public User create(User entity) {
        try {
            HttpEntity<User> userHttpEntity = new HttpEntity<>(entity);
            return restTemplate.postForObject(URL, userHttpEntity, User.class);
        } catch (HttpClientErrorException ex) {
            if(ex.getStatusCode().value() == 409) {
                log.error("Data integrity exception: {}", ex.getResponseBodyAsString());
            }
            else {
                log.error("Error with creating new user, responseBody: " + ex.getResponseBodyAsString());
            }
            throw new RuntimeException("An error occurred while creating new user: " + ex.getResponseBodyAsString());
        }
    }

    @Override
    public User updateById(Integer id, User entity) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        try {
            restTemplate.delete(URL + "/{id}", id);
        } catch (HttpClientErrorException ex) {
            log.error("Error with User {ID: " + id + "}, responseBody: " + ex.getResponseBodyAsString());

            if (ex.getStatusCode().value() == 404) {
                throw new UserNotFoundException(id);
            }

            throw new RuntimeException("An error occurred while deleting user {ID " + id + "}, responseBody: " + ex.getResponseBodyAsString());
        }
    }

    @Override
    public boolean isServerAlive() {
        try {
            restTemplate.exchange("http://localhost:8080/status", HttpMethod.HEAD, null, String.class);
            return true;
        }
        catch (ResourceAccessException ex) {
            log.warn("Server is not alive: " + ex.getMessage());
            return false;
        }
        catch (HttpStatusCodeException ex) {
            log.warn("Server responded with error status: {} - {}", ex.getStatusCode(), ex.getStatusText());
            return false;
        }
        catch (Exception ex) {
            log.error("Unexpected error occurred while checking server status", ex);
            return false;
        }
    }
}
