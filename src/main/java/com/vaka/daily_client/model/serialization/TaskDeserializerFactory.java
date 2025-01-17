package com.vaka.daily_client.model.serialization;

import com.vaka.daily_client.client.blocked.TaskTypeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskDeserializerFactory {
    private final TaskTypeClient taskTypeClient;

    @Autowired
    public TaskDeserializerFactory(TaskTypeClient taskTypeClient) {
        this.taskTypeClient = taskTypeClient;
    }

    public TaskDeserializer create() {
        return new TaskDeserializer(taskTypeClient);
    }
}
