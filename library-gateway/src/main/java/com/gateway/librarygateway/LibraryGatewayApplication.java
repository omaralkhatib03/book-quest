package com.gateway.librarygateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;



@SpringBootApplication
@EnableZuulProxy
public class LibraryGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryGatewayApplication.class, args);
	}

}
