package com.gateway.librarygateway.filters;

import com.gateway.librarygateway.context.ApiContext;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import java.net.http.HttpResponse;

@Log4j2
@RequiredArgsConstructor
@Component
public class PreFilter extends ZuulFilter {

    private final HttpClient httpClient;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() { // only filter if were accessing the data microservice
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest req = ctx.getRequest();
        String uri = req.getRequestURI();
        log.info("URI: " + uri);
        log.info(uri.matches("/data/?.*"));
        return uri.matches("/data/?.*");
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        
        log.info("Method: " + request.getMethod());

        if (request.getMethod().equals("OPTIONS"))
            return null;


        final String authorizationHeader = request.getHeader("Authorization");
        
        log.info("PreFilter");
        log.info(authorizationHeader);
        
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            log.info("Invalid Authorization Header !");
            response.setStatus(200);
            ctx.setSendZuulResponse(false);
            return null;
        }

        final String token = authorizationHeader.substring(7);

        if (token.equals("null")) {
            response.setStatus(403);
            ctx.setSendZuulResponse(false);
            return null;
        }

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(ApiContext.getAuthURL() + "/validate"))
                .header("Authorization", "Bearer " + token)
                .method("OPTIONS", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> res = null;

        log.info("AuthFilter");
        log.info("Hitting: " + ApiContext.getAuthURL() + "/validate");
        
        try {
            res = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            log.info(res.statusCode());
        } catch (Exception e) {
            log.info("Error while validating token !");
            response.setStatus(403);
            ctx.setSendZuulResponse(false);
            return null;
        }

        if (res.statusCode() < 200 || res.statusCode() >= 300) {
            response.setStatus(403);
            ctx.setSendZuulResponse(false);
        }

        return null;

    }
}
