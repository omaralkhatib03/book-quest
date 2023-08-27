package com.gateway.librarygateway.filters;

import java.net.http.HttpClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientBean {
        @Bean
        public HttpClient httpClient() {
            return HttpClient.newBuilder().build();
        }
}
