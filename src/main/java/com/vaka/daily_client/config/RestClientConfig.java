package com.vaka.daily_client.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaka.daily_client.error_handling.JSONResponseErrorHandler;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
//                .requestInterceptor((request, body, execution) -> {
//                    log.info("{} | {}", request.getMethod(), request.getURI());
//                    log.info(new String(body));
//                    return null;
//                })
                .build();
    }
}
