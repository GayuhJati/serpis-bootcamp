package com.bootcamp.techno.orderservice.service;

import com.bootcamp.techno.orderservice.model.APIResponse;
import com.bootcamp.techno.orderservice.model.request.ReqOrderDto;
import com.bootcamp.techno.orderservice.model.response.ResOrderDto;

import java.util.List;

public interface OrderService {
    APIResponse<String> addOrder(ReqOrderDto request);
    APIResponse<String> updateOrder(Long id, String status);
    APIResponse<List<ResOrderDto>> getAllOrderById(Long id);
}
