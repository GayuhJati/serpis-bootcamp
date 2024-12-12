package com.bootcamp.techno.orderservice.controller;


import com.bootcamp.techno.orderservice.model.APIResponse;
import com.bootcamp.techno.orderservice.model.request.ReqOrderDto;
import com.bootcamp.techno.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/ord")
@RequiredArgsConstructor
public class OrdController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<APIResponse<String>> addOrder(@RequestBody ReqOrderDto request) {
        return ResponseEntity.ok(orderService.addOrder(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<String>> updateOrder(@PathVariable Long id, @RequestBody ReqOrderDto request) {
        return ResponseEntity.ok(orderService.updateOrder(id, request));
    }

}
