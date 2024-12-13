package com.techno.bootcamp.paymentservice.controller;


import com.techno.bootcamp.paymentservice.model.APIResponse;
import com.techno.bootcamp.paymentservice.model.request.ReqPaymentDto;
import com.techno.bootcamp.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/pay")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<APIResponse<String>> makePay(@RequestBody ReqPaymentDto request) {
        return ResponseEntity.ok(paymentService.updateStatus(request));    }
}
