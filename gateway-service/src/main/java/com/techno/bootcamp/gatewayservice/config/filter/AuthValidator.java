package com.techno.bootcamp.gatewayservice.config.filter;


import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;


import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
public class AuthValidator {

    private final List<String> endpoints = Arrays.asList(
            "/auth/v1/api/users/login",
            "/auth/v1/api/users/register"
    );

    public Predicate<ServerHttpRequest> isSecure = request -> endpoints.stream().noneMatch(uri -> request.getURI().getPath().contains(uri));

}
