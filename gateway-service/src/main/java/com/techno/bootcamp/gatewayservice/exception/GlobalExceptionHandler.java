package com.techno.bootcamp.gatewayservice.exception;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler {

    public static Mono<Void> onError(ServerWebExchange exchange, String err, String message, HttpStatus httpStatus){
        ServerHttpResponse response = exchange.getResponse();

        response.setStatusCode(HttpStatus.valueOf(httpStatus.value()));

        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        Map<String,Object> body = Map.of(
                "reqId", UUID.randomUUID(),
                "status", httpStatus.value(),
                "message", message,
                "error", err
        );

        try {
            String res = new ObjectMapper().writeValueAsString(body);
            byte[] bytes = res.getBytes(StandardCharsets.UTF_8);

            return response.writeWith(Flux.just(response.bufferFactory().wrap(bytes)));
        }catch (Exception e) {
            return Mono.error(e);
        }
    }

}
