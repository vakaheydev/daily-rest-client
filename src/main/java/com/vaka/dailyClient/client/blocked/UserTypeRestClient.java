package com.vaka.dailyClient.client.blocked;

import com.vaka.dailyClient.model.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserTypeRestClient extends AbstractRestClient<UserType> implements UserTypeClient {
    org.springframework.web.client.RestClient client;

    @Autowired
    public UserTypeRestClient(org.springframework.web.client.RestClient client) {
        this.client = client;
    }

    @Override
    public org.springframework.web.client.RestClient getRestClient() {
        return client;
    }

    @Override
    public String getDomainUrl() {
        return "/api/user_type";
    }

    @Override
    public String getNameOfUniqueName() {
        return "name";
    }

    @Override
    public Class<UserType> getDomainType() {
        return UserType.class;
    }
}