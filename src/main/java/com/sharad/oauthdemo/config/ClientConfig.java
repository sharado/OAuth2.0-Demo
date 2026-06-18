package com.sharad.oauthdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.client.OAuth2ClientHttpRequestInterceptor;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClientConfig {

    @Bean
    RestClient restClient(OAuth2AuthorizedClientManager manager) {

        OAuth2ClientHttpRequestInterceptor interceptor = new OAuth2ClientHttpRequestInterceptor(manager);

        return RestClient.builder()
                .requestInterceptor(interceptor)
                .build();
    }

    @Bean
    RestTemplate restTemplate(OAuth2AuthorizedClientManager manager) {

        OAuth2ClientHttpRequestInterceptor interceptor = new OAuth2ClientHttpRequestInterceptor(manager);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(interceptor);

        return restTemplate;
    }
}
