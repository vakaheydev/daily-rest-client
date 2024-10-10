package com.vaka.daily_client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@PropertySource("classpath:application.properties")
public class WebClientConfig {
    @Value("app.connection.url")
    private String url;

    @Bean
    public WebClient webClient() {
        return WebClient.create(url);
    }
}
