package com.vaka.daily_client.model.serialization;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.vaka.daily_client.client.blocked.TaskTypeClient;
import com.vaka.daily_client.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class TaskDeserializer extends JsonDeserializer<Task> {
    private TaskTypeClient taskTypeClient;

    @Autowired
    public TaskDeserializer(@Lazy TaskTypeClient taskTypeClient) {
        this.taskTypeClient = taskTypeClient;
    }

    @Override
    public Task deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode node = p.getCodec().readTree(p);

        Task task = Task.builder()
                .id(node.get("id").asInt())
                .name(node.get("name").asText())
                .description(node.get("description").asText())
                .deadline(LocalDateTime.parse(node.get("deadline").asText()))
                .status(node.get("status").asBoolean())
                .scheduleId(node.get("scheduleId").asInt())
                .build();


        if (node.get("taskTypeId") == null && node.get("taskType") == null) {
            throw new IllegalStateException("Task with no task type is illegal");
        }

        if (node.get("taskTypeId") != null) {
            task.setTaskType(taskTypeClient.getById(node.get("taskTypeId").asInt()));
        } else {
            task.setTaskType(taskTypeClient.getById(node.get("taskType").get("taskTypeId").asInt()));
        }

        return task;
    }
}
