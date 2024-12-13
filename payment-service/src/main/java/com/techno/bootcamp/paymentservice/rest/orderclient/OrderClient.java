package com.techno.bootcamp.paymentservice.rest.orderclient;

import com.techno.bootcamp.paymentservice.rest.orderclient.dto.response.ResWrapperOrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "order-service",
        url = "http://localhost:9005",
        path = "/order/v1/api"
)
public interface OrderClient {

    @GetMapping("/ord/{id}")
    public ResponseEntity<ResWrapperOrderDto> getOrderById(@PathVariable("id") Long id);

    @PutMapping("/ord/{id}")
    public ResponseEntity<ResWrapperOrderDto> updateOrderById(@PathVariable("id") Long id);
}
