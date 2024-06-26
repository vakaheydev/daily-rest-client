package com.vaka.daily_client.client.blocked;

import com.vaka.daily_client.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@Slf4j
public class UserRestClient extends AbstractRestClient<User> implements UserClient {
    RestClient restClient;

    @Autowired
    public UserRestClient(RestClient restClient) {
        this.restClient = restClient;
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
