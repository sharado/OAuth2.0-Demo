package com.sharad.oauthdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class Controller1 {

    @Autowired
    private RestClient restClient;

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/home")
    public String getAllEmployees(){
        return "Welcome to Home Page!!";
    }

    @GetMapping("/secured")
    public String getSecuredPage(){
        return "Secured Page!!";
    }

    @GetMapping("/google/rest-client")
    public Map<String,Object> callGoogleViaRestClient() {

        return restClient.get()
                .uri("https://www.googleapis.com/oauth2/v2/userinfo")
                .retrieve()
                .body(Map.class);
    }

    @GetMapping("/google/rest-template")
    public Map<String,Object> callGoogleViaRestTemplate() {

        return restTemplate.getForObject(
                "https://www.googleapis.com/oauth2/v2/userinfo",
                Map.class
        );
    }
}
