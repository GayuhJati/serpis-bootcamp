package com.bootcamp.techno.orderservice.controller;

import com.bootcamp.techno.orderservice.model.APIResponse;
import com.bootcamp.techno.orderservice.model.request.ReqOrderDto;
import com.bootcamp.techno.orderservice.model.response.ResOrderDto;
import com.bootcamp.techno.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/ord")
@RequiredArgsConstructor
public class OrdController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<APIResponse<String>> addOrder(@RequestBody ReqOrderDto request) {
        APIResponse<String> response = orderService.addOrder(request);
        if (response.getData() != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<List<ResOrderDto>>> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getAllOrderById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<String>> updateOrder(@PathVariable Long id, @RequestParam String status) {
        if (status == null || status.isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new APIResponse<>("FAILED")
            );
        }
        APIResponse<String> response = orderService.updateOrder(id, status);
        if ("SUCCESS".equals(response.getStatus())) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
