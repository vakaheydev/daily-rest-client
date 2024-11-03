package com.vaka.daily_client.client.blocked;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.vaka.daily_client.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
@Slf4j
public class UserRestClient extends AbstractRestClient<User> implements UserClient {
    private ObjectMapper objectMapper;
    RestClient restClient;

    @Value("${app.connection.url}")
    private String URL;

    public UserRestClient(ObjectMapper objectMapper, RestClient restClient) {
        this.objectMapper = objectMapper;
        this.restClient = restClient;
    }

    @Override
    public List<User> getByUserTypeName(String userTypeName) {
        String response = restClient.get()
                .uri(URL + getDomainUrl() + "/search?user_type_name=" + userTypeName)
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

    @Override
    public RestClient getRestClient() {
        return restClient;
    }

    @Override
    public String getDomainUrl() {
        return "/api/user";
    }

    @Override
    public String getNameOfUniqueName() {
        return "login";
    }

    @Override
    public Class<User> getDomainType() {
        return User.class;
    }
}
