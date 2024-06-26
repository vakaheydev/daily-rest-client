package com.vaka.daily_client.client.blocked;

import com.vaka.daily_client.client.CommonClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;

import java.util.List;

/**
 * Abstract rest client. Uses {@link org.springframework.web.client.RestClient RestClient} for its internal work
 *
 * @param <T> Domain type
 */
@Slf4j
public abstract class AbstractRestClient<T> implements CommonClient<T> {
    @Value("${app.connection.url}")
    private String URL;

    @Override
    public List<T> getAll() {
        return getRestClient().get()
                .uri(URL + getDomainUrl())
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    @Override
    public T getById(Integer id) {
        return getRestClient().get()
                .uri(URL + getDomainUrl() + "/{id}", id)
                .retrieve()
                .body(getDomainType());
    }

    @Override
    public T getByUniqueName(String uniqueName) {
        return getRestClient().get()
                .uri(URL + getDomainUrl() + "/search?" + getNameOfUniqueName() + "={name}", uniqueName)
                .retrieve()
                .body(getDomainType());
    }

    @Override
    public T create(T entity) {
        return getRestClient().post()
                .uri(URL + getDomainUrl())
                .contentType(MediaType.APPLICATION_JSON)
                .body(entity)
                .retrieve()
                .body(getDomainType());
    }

    @Override
    public T updateById(Integer id, T entity) {
        return getRestClient().put()
                .uri(URL + getDomainUrl() + "/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(entity)
                .retrieve()
                .body(getDomainType());
    }

    @Override
    public void deleteById(Integer id) {
        getRestClient().delete()
                .uri(URL + getDomainUrl() + "/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }

    @Override
    public boolean isServerAlive() {
        try {
            getRestClient().head()
                    .uri(URL + "/status")
                    .retrieve()
                    .body(String.class);
            return true;
        } catch (ResourceAccessException ex) {
            log.error("Server is not responding! {}", ex.getMessage());
            return false;
        }
    }

    public abstract RestClient getRestClient();

    /**
     * Get domain part of URL e.g. "/api/user"
     *
     * @return Domain part of URL
     */
    public abstract String getDomainUrl();

    /**
     * Get name of entity unique name. For example for {@link com.vaka.daily_client.model.User User} it is "login", for
     * {@link com.vaka.daily_client.model.Task Task} - "name"
     *
     * @return Name of entity unique name
     */
    public abstract String getNameOfUniqueName();

    public abstract Class<T> getDomainType();
}
