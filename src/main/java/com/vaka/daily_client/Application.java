package com.vaka.daily_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

//        UserRestClient client = ctx.getBean("userRestClient", UserRestClient.class);
//        if(client.isServerAlive()) {
//            User user = new User();
//            client.create(user);
//        }
    }
}
