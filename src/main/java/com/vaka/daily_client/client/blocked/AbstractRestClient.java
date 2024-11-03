package com.vaka.daily_client.client.blocked;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.vaka.daily_client.client.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public abstract class AbstractRestClient<T> implements Client<T> {
    @Value("${app.connection.url}")
    protected String URL;

    protected ObjectMapper objectMapper;

    /**
     * Retrieve all objects of type {@code T} from the REST API.
     *
     * @return a list of objects of type {@code T}
     * @throws RuntimeException if there is an error during JSON processing
     */
    @Override
    public List<T> getAll() {
        String response = getRestClient().get()
                .uri(URL + getDomainUrl())
                .retrieve()
                .body(String.class);

        try {
            return objectMapper.readValue(response,
                    TypeFactory.defaultInstance().constructCollectionType(List.class, getDomainType()));
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieve an object of type {@code T} by its ID from the REST API.
     *
     * @param id the ID of the object to retrieve
     * @return the object of type {@code T} with the specified ID
     */
    @Override
    public T getById(Integer id) {
        return getRestClient().get()
                .uri(URL + getDomainUrl() + "/{id}", id)
                .retrieve()
                .body(getDomainType());
    }

    /**
     * Retrieve an object of type {@code T} by its unique name from the REST API.
     *
     * @param uniqueName the unique name of the object to retrieve
     * @return the object of type {@code T} with the specified unique name
     */
    @Override
    public T getByUniqueName(String uniqueName) {
        return getRestClient().get()
                .uri(URL + getDomainUrl() + "/search?" + getNameOfUniqueName() + "={name}", uniqueName)
                .retrieve()
                .body(getDomainType());
    }

    /**
     * Create an object of type {@code T} in the REST API and return it.
     *
     * @param entity the object to create
     * @return the created object
     */
    @Override
    public T create(T entity) {
        return getRestClient().post()
                .uri(URL + getDomainUrl())
                .contentType(MediaType.APPLICATION_JSON)
                .body(entity)
                .retrieve()
                .body(getDomainType());
    }

    /**
     * Update an object of type {@code T} with the specified ID in the REST API.
     *
     * @param id     the id of the object to update
     * @param entity the object to update
     * @return the updated object with the specified ID
     */
    @Override
    public T updateById(Integer id, T entity) {
        return getRestClient().put()
                .uri(URL + getDomainUrl() + "/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(entity)
                .retrieve()
                .body(getDomainType());
    }

    /**
     * Delete an object by specified ID in the REST API.
     *
     * @param id the id of the object to delete
     */
    @Override
    public void deleteById(Integer id) {
        getRestClient().delete()
                .uri(URL + getDomainUrl() + "/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }

    /**
     * Check whether the server is alive by performing HEAD request to the REST API.
     *
     * @return true if the server is alive; false otherwise
     */
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

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}
