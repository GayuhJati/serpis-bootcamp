package com.techno.bootcamp.productservice.rest.authclient;


import com.techno.bootcamp.productservice.rest.authclient.dto.response.ResUserDto;
import com.techno.bootcamp.productservice.rest.authclient.dto.response.ResWrapperDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "auth-service",
        url = "http://localhost:9001",
        path = "/auth/v1/api"
)
public interface AuthClient {
    @GetMapping("/users/{id}")
    public ResponseEntity<ResWrapperDto> getUserById(@PathVariable("id") Long id);
}