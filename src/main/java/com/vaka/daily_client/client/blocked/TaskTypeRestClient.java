package com.vaka.daily_client.client.blocked;

import com.vaka.daily_client.model.TaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class TaskTypeRestClient extends AbstractRestClient<TaskType> implements TaskTypeClient {
    RestClient client;

    @Autowired
    public TaskTypeRestClient(RestClient client) {
        this.client = client;
    }

    @Override
    public RestClient getRestClient() {
        return client;
    }

    @Override
    public String getDomainUrl() {
        return "/api/task_type";
    }

    @Override
    public String getNameOfUniqueName() {
        return "name";
    }

    @Override
    public Class<TaskType> getDomainType() {
        return TaskType.class;
    }
}
