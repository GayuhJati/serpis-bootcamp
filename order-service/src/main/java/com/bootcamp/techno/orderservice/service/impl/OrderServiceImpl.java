package com.bootcamp.techno.orderservice.service.impl;

import com.bootcamp.techno.orderservice.entity.OrderEntity;
import com.bootcamp.techno.orderservice.model.APIResponse;
import com.bootcamp.techno.orderservice.model.request.ReqOrderDto;
import com.bootcamp.techno.orderservice.repository.OrderRepository;
import com.bootcamp.techno.orderservice.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final HttpServletRequest httpServletRequest;

    @Override
    public APIResponse<String> addOrder(ReqOrderDto request) {
        if (request.getIdProduct() == null || request.getTotalPrice() == null) {
            throw new IllegalArgumentException("Invalid order details");
        }

        OrderEntity entity = modelMapper.map(request, OrderEntity.class);
        entity.setIdUser(Long.valueOf(httpServletRequest.getHeader("idUser")));
        orderRepository.save(entity);
        return new APIResponse<>("Order Created");
    }

    @Override
    public APIResponse<String> updateOrder(Long id, ReqOrderDto request) {
        return null;
    }
}
