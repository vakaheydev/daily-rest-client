package com.vaka.daily;

import com.vaka.daily.client.UserRestClientClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

//        UserRestClientClient client = ctx.getBean("userRestClientClient", UserRestClientClient.class);
//        if(client.isServerAlive()) {
//            client.getById(5);
//        }
    }
}
