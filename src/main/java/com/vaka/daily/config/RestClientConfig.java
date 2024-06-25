package com.vaka.daily.config;

import com.vaka.daily.util.MyResponseErrorHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestClient;

@Configuration
@PropertySource("classpath:application.properties")
public class RestClientConfig {
    @Value("app.connection.url")
    private String url;

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .baseUrl(url)
                .defaultHeader("Content-Type", "application/json")
                .defaultStatusHandler(new MyResponseErrorHandler())
                .build();
    }
}
