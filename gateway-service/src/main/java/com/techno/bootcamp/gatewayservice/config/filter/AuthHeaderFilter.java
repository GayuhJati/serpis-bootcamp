package com.techno.bootcamp.gatewayservice.config.filter;


import com.techno.bootcamp.gatewayservice.exception.GlobalExceptionHandler;
import com.techno.bootcamp.gatewayservice.util.Constant;
import com.techno.bootcamp.gatewayservice.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RefreshScope
@Component
@RequiredArgsConstructor
public class AuthHeaderFilter implements GatewayFilter {

    private final AuthValidator authValidator;
    private final JwtUtil jwtUtil;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = (ServerHttpRequest) exchange.getRequest();
        if (authValidator.isSecure.test(request)) {
            if (isAuthMissing(request)) {
                return GlobalExceptionHandler.onError(
                        exchange,
                        Constant.DONT_HAVE_PERMISSIONS_MESSAGE,
                        "Authorization header is missing",
                        HttpStatus.UNAUTHORIZED
                );
            }
            String token = getAuthToken(request);
            if (jwtUtil.isInvalid(token)){
                return GlobalExceptionHandler.onError(
                        exchange,
                        Constant.DONT_HAVE_PERMISSIONS_MESSAGE,
                        "Invalid token or expired token",
                        HttpStatus.FORBIDDEN
                );
            }
            populateRequestWithHeaders(exchange, token);
        }
        return chain.filter(exchange);
    }

    private boolean isAuthMissing(ServerHttpRequest request){
        return !request.getHeaders().containsKey("Authorization");
    }

    private String getAuthToken(ServerHttpRequest request){
        String authHeader = request.getHeaders().getFirst("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")){
            return authHeader.substring(7);
        }
        return "";
    }

    private void populateRequestWithHeaders(ServerWebExchange exchange, String token){
        try{
            Claims claims = jwtUtil.decodeJwt(token);
            System.out.println("idUser: " + claims.get("idUser"));
            System.out.println("email: " + claims.get("email"));
            System.out.println("role: " + claims.get("role"));
            exchange.getRequest().mutate()
                    .header("idUser", claims.get("idUser").toString())
                    .header("email", claims.get("email").toString())
                    .header("role", claims.get("role").toString())
                    .build();
        } catch (JwtException e) {
            e.printStackTrace();
        }
    }
}
