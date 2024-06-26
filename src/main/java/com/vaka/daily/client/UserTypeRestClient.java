package com.vaka.daily.client;

import com.vaka.daily.model.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class UserTypeRestClient extends AbstractRestClient<UserType> {
    RestClient client;

    @Autowired
    public UserTypeRestClient(RestClient client) {
        this.client = client;
    }

    @Override
    public RestClient getRestClient() {
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
