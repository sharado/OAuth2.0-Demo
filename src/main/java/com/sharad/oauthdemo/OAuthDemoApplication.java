package com.sharad.oauthdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class OAuthDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(OAuthDemoApplication.class, args);
        Arrays.stream(configurableApplicationContext.getBeanDefinitionNames()).forEach(System.out::println);
    }

}
