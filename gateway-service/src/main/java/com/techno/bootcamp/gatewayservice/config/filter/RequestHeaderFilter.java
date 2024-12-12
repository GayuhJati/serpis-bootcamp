package com.techno.bootcamp.gatewayservice.config.filter;


import com.techno.bootcamp.gatewayservice.exception.GlobalExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RefreshScope
@Component
public class RequestHeaderFilter implements GatewayFilter {

    @Value("${header.security.xContentTypeOptions}")
    private String xContentTypeOptions;

    @Value("${header.security.xXssProtection}")
    private String xXssProtection;

    @Value("${header.security.strictTransportSecurity}")
    private String strictTransportSecurity;

    @Value("${header.security.xFramerOptions}")
    private String xFramerOptions;

    @Value("${header.security.apiKey}")
    private String apiKey;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders headers = exchange.getRequest().getHeaders();

        String transport = headers.getFirst("Strict-Transport-Security");
        String contentTypeOption = headers.getFirst("X-Content-Type-Option");
        String framerOption = headers.getFirst("X-Frame-Options");
        String xssProtection = headers.getFirst("X-XSS-Protection");
        String headerApiKey = headers.getFirst("X-Api-Key");

        boolean isHeaderValid = strictTransportSecurity.equals(transport) &&
                                xContentTypeOptions.equals(contentTypeOption) &&
                                xFramerOptions.equals(framerOption) &&
                                xXssProtection.equals(xssProtection) &&
                                apiKey.equals(headerApiKey);

        boolean isHeaderValid1 = strictTransportSecurity.equals(transport);
        boolean isHeaderValid2 = xContentTypeOptions.equals(contentTypeOption);
        boolean isHeaderValid3 = xFramerOptions.equals(framerOption);
        boolean isHeaderValid4 = xXssProtection.equals(xssProtection);
        boolean isHeaderValid5 = apiKey.equals(headerApiKey);

        if (!isHeaderValid) {
            return GlobalExceptionHandler.onError(exchange,"You shall not pass !!!", "UNATHORIZED", HttpStatus.UNAUTHORIZED);
        }

        return chain.filter(exchange);
    }
}
