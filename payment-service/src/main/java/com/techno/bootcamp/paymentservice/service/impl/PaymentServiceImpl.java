package com.techno.bootcamp.paymentservice.service.impl;

import com.techno.bootcamp.paymentservice.entity.PaymentEntity;
import com.techno.bootcamp.paymentservice.model.APIResponse;
import com.techno.bootcamp.paymentservice.model.request.ReqPaymentDto;
import com.techno.bootcamp.paymentservice.repository.PaymentRepository;
import com.techno.bootcamp.paymentservice.rest.orderclient.OrderClient;
import com.techno.bootcamp.paymentservice.rest.orderclient.dto.response.ResWrapperOrderDto;
import com.techno.bootcamp.paymentservice.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final HttpServletRequest httpServletRequest;
    private final OrderClient orderClient;

    @Override
    public APIResponse<String> makePay(ReqPaymentDto request) {
        String role = httpServletRequest.getHeader("role");

        if (!"CUSTOMER".equals(role)) {
            throw new RuntimeException("You don't have permission to create");
        }

        ResWrapperOrderDto orderResponse = orderClient.getOrderById(request.getOrderId()).getBody();

        if (orderResponse == null) {
            return new APIResponse<>("Order Not Found");
        }

        if (!orderResponse.getData().getOrderTotal().equals(request.getAmount())) {
            return new APIResponse<>("Payment Failed: Amount does not match the order total");
        }

        PaymentEntity payment = new PaymentEntity();
        payment.setOrderId(request.getOrderId());
        payment.setAmount(request.getAmount());
        payment.setStatus("PAID");

        paymentRepository.save(payment);
        return new APIResponse<>("Payment Success");
    }

    @Override
    public APIResponse<String> updateStatus(ReqPaymentDto request) {
        String role = httpServletRequest.getHeader("role");

        if (!"CUSTOMER".equals(role)) {
            throw new RuntimeException("You don't have permission to create");
        }

        ResWrapperOrderDto orderResponse = orderClient.updateOrderById(request.getOrderId()).getBody();

        return new APIResponse<>("success");
    }
}
