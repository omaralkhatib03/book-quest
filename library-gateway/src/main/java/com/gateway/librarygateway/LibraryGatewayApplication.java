package com.gateway.librarygateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.gateway.librarygateway.filters.ErrorFilter;
import com.gateway.librarygateway.filters.PostFilter;
import com.gateway.librarygateway.filters.PreFilter;
import com.gateway.librarygateway.filters.RouteFilter;

@SpringBootApplication
@EnableZuulProxy
public class LibraryGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryGatewayApplication.class, args);
	}
	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}
	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}
	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}
	@Bean
	public RouteFilter routeFilter() {
		return new RouteFilter();
	}

}
