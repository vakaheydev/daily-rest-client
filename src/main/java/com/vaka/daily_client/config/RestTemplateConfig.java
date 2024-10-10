package com.vaka.daily_client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
@PropertySource("classpath:application.properties")
public class RestTemplateConfig {
    /**
     * Connection timeout in milliseconds
     */
    @Value("${app.connection.timeout:1000}")
    long connectionTimeoutMs;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplateBuilder builder = new RestTemplateBuilder();

        Duration connectionDurationTimeout = Duration.ofMillis(connectionTimeoutMs);

        return builder
                .setConnectTimeout(connectionDurationTimeout)
                .setReadTimeout(connectionDurationTimeout)
                .build();
    }
}
