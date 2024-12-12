package com.bootcamp.techno.orderservice.rest.productclient;

import com.bootcamp.techno.orderservice.rest.productclient.dto.response.ResProductDto;
import com.bootcamp.techno.orderservice.rest.productclient.dto.response.ResWrapperProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "product-service",
        url = "http://localhost:9005",
        path = "/product/v1/api"
)
public interface ProductClient {

    @GetMapping("/prod/{id}")
    public ResponseEntity<ResWrapperProductDto> getProductById(@PathVariable("id") Long id);
}