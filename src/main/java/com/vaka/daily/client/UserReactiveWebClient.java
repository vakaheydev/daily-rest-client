package com.vaka.daily.client;

import com.vaka.daily.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@Slf4j
public class UserReactiveWebClient implements UserReactiveClient {
    private final WebClient webClient;

    @Autowired
    public UserReactiveWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public boolean isServerAlive() {
        return false;
    }

    @Override
    public Mono<List<User>> getAll() {
        ParameterizedTypeReference<List<User>> usersList = new ParameterizedTypeReference<List<User>>() {
        };

        log.info("Running getAll()");

        return webClient.get()
                .uri("/api/user")
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError,
                        error -> Mono.error(new RuntimeException("API not found")))
                .bodyToMono(usersList)
                .doOnError(error -> log.error("An error has occurred {}", error.getMessage()));

    }

    public List<User> blockedGetAll() {
        ParameterizedTypeReference<List<User>> usersList = new ParameterizedTypeReference<List<User>>() {
        };

        log.info("Running getAll()");

        return webClient.get()
                .uri("/api/user")
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError,
                        error -> Mono.error(new RuntimeException("API not found")))
                .bodyToMono(usersList)
                .doOnError(error -> log.error("An error has occurred {}", error.getMessage()))
                .block();

    }

    @Override
    public Mono<User> getById(Integer id) {
        return null;
    }

    @Override
    public Mono<User> create(User entity) {
        return null;
    }

    @Override
    public Mono<User> updateById(Integer id, User entity) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
