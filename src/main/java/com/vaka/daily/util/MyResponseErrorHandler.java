package com.vaka.daily.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaka.daily.exception.UserNotFoundException;
import com.vaka.daily.model.ResponseError;
import com.vaka.daily.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

@Slf4j
public class MyResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return !response.getStatusCode().is2xxSuccessful();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        log.error("An error occurred in RestClient request, response: {} : {}", response.getStatusText(),
                response.getStatusCode());
    }

    @Override
    public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {
        String[] urlSplit = url.getPath().split("/");
        String domainURLType = urlSplit[2];

        // Response ERROR: ResponseError(error=UserNotFoundException, message=User with unique name {abc} not found, status=404)
        // Response ERROR: ResponseError(error=UserNotFoundException, message=User with ID {4} not found, status=404)

        InputStream responseBody = response.getBody();
        String body = new BufferedReader(new InputStreamReader(responseBody)).readLine();

        ObjectMapper objectMapper = new ObjectMapper();
        ResponseError error = objectMapper.readValue(body, ResponseError.class);

        log.error("Response ERROR: {}", error.toString());

        switch (domainURLType) {
            case "user": {
                if (error.getError().equals("UserNotFoundException")) {
                    String[] splitResolvedException = resolveNotFoundException(error).split("\\|");
                    if(splitResolvedException[0].equals("id")) {
                        throw new UserNotFoundException(Integer.parseInt(splitResolvedException[1]));
                    }
                    else {
                        throw new UserNotFoundException(splitResolvedException[1]);
                    }
                }
            }

            case "user_type": {

            }
        }

        log.error("An error occurred while making request {} {} : {}", method.toString(), url.toString(),
                response.getStatusCode());
    }

    private static String resolveNotFoundException(ResponseError error) {
        if(error.getMessage().contains("ID {")) {
            String id = error.getMessage().split("\\{")[1].split("}")[0];
            return "id|" + id;
        }
        else if (error.getMessage().contains("unique name {")){
            String name = error.getMessage().split("\\{")[1].split("}")[0];
            return "name|" + name;
        }
        else {
            throw new RuntimeException("Error during resolving not found exception with message: " + error.getMessage());
        }
    }
}
