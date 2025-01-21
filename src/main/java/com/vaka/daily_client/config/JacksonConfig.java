package com.vaka.daily_client.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.vaka.daily_client.model.ResponseError;
import com.vaka.daily_client.model.Task;
import com.vaka.daily_client.model.serialization.ResponseErrorDeserializer;
import com.vaka.daily_client.model.serialization.TaskDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = "com.vaka.daily_client.model.serialization")
@Configuration
public class JacksonConfig {
    private TaskDeserializer taskDeserializer;

    @Autowired
    public JacksonConfig(TaskDeserializer taskDeserializer) {
        this.taskDeserializer = taskDeserializer;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addDeserializer(ResponseError.class, new ResponseErrorDeserializer());
        module.addDeserializer(Task.class, taskDeserializer);

        objectMapper.registerModule(module);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(new Jdk8Module());

        return objectMapper;
    }
}
