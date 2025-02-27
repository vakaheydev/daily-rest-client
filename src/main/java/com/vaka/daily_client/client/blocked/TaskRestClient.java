package com.vaka.daily_client.client.blocked;

import com.vaka.daily_client.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class TaskRestClient extends AbstractRestClient<Task> implements TaskClient {
    RestClient client;

    @Autowired
    public TaskRestClient(RestClient client) {
        this.client = client;
    }

    @Override
    public RestClient getRestClient() {
        return client;
    }

    @Override
    public String getDomainUrl() {
        return "/api/task";
    }

    @Override
    public String getNameOfUniqueName() {
        return "name";
    }

    @Override
    public Class<Task> getDomainType() {
        return Task.class;
    }

    @Override
    public Task getByUniqueName(String uniqueName) {
        throw new IllegalStateException("Task doesn't have unique name");
    }
}
