package com.techno.bootcamp.paymentservice.service;

import com.techno.bootcamp.paymentservice.model.APIResponse;
import com.techno.bootcamp.paymentservice.model.request.ReqPaymentDto;

public interface PaymentService {
    APIResponse<String> makePay(ReqPaymentDto request);
    APIResponse<String> updateStatus(ReqPaymentDto request);
}
