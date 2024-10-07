package com.vaka.dailyClient.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaka.dailyClient.errorHandling.JSONResponseErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestClient;

@Configuration
@PropertySource("classpath:application.properties")
@Import(JacksonConfig.class)
public class RestClientConfig {
    private final ObjectMapper objectMapper;

    @Autowired
    public RestClientConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Value("app.connection.url")
    private String url;

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .baseUrl(url)
                .defaultHeader("Content-Type", "application/json")
                .defaultStatusHandler(new JSONResponseErrorHandler(objectMapper))
                .build();
    }
}