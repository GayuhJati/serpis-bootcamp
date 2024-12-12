package com.bootcamp.techno.orderservice.service;

import com.bootcamp.techno.orderservice.model.APIResponse;
import com.bootcamp.techno.orderservice.model.request.ReqOrderDto;

public interface OrderService {
    APIResponse<String> addOrder(ReqOrderDto request);
    APIResponse<String> updateOrder(Long id, ReqOrderDto request);
}
