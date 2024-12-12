package com.techno.bootcamp.gatewayservice.config;


import com.techno.bootcamp.gatewayservice.config.filter.AuthHeaderFilter;
import com.techno.bootcamp.gatewayservice.config.filter.RequestHeaderFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    private final RequestHeaderFilter requestHeaderFilter;
    private final AuthHeaderFilter authHeaderFilter;

    public GatewayConfig(RequestHeaderFilter requestHeaderFilter, AuthHeaderFilter authHeaderFilter) {
        this.requestHeaderFilter = requestHeaderFilter;
        this.authHeaderFilter = authHeaderFilter;
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("auth-service", r -> r.path("/gateway/auth/**")
                        .filters(f -> f.rewritePath("/gateway/auth/(?<segment>.*)","/auth/$\\{segment}")
                                .filter(requestHeaderFilter)
                                .filter(authHeaderFilter)
                        ).uri("lb://auth-service"))
                .route("product-service",r->r.path("/gateway/prod/**")
                        .filters(f -> f.rewritePath("/gateway/prod/(?<segment>.*)","/prod/$\\{segment}")
                        .filter(requestHeaderFilter))
                        .uri("lb://prod-service"))
                .route("order-service", r->r.path("/gateway/ord/**")
                        .filters(f -> f.rewritePath("/gateway/ord/(?<segment>.*)","/ord/$\\{segment}")
                                .filter(requestHeaderFilter))
                        .uri("lb://ord-service"))
                .build();
    }
}
