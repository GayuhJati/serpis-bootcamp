package com.techno.bootcamp.productservice.rest.authclient;


import com.techno.bootcamp.productservice.rest.authclient.dto.response.ResUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
    name = "auth-service",
    url = "localhost:9002",
    path = "/auth/v1/api"
)

public interface AuthClient {

    @GetMapping("/users/{id}")
    public ResponseEntity<ResUserDto>getUserById(@PathVariable("id") Long id);
}
