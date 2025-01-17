package com.vaka.daily_client.model.serialization;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.vaka.daily_client.client.blocked.TaskTypeClient;
import com.vaka.daily_client.model.Task;

import java.io.IOException;
import java.time.LocalDateTime;

public class TaskDeserializer extends JsonDeserializer<Task> {
    private TaskTypeClient taskTypeClient;

    public TaskDeserializer(TaskTypeClient taskTypeClient) {
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
                .taskType(taskTypeClient.getById(node.get("taskTypeId").asInt()))
                .scheduleId(node.get("scheduleId").asInt())
                .build();

        return task;
    }
}
