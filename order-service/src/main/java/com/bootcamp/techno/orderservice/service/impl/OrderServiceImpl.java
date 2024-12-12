package com.bootcamp.techno.orderservice.service.impl;

import com.bootcamp.techno.orderservice.entity.OrderEntity;
import com.bootcamp.techno.orderservice.model.APIResponse;
import com.bootcamp.techno.orderservice.model.request.ReqOrderDto;
import com.bootcamp.techno.orderservice.repository.OrderRepository;
import com.bootcamp.techno.orderservice.rest.productclient.ProductClient;
import com.bootcamp.techno.orderservice.rest.productclient.dto.response.ResProductDto;
import com.bootcamp.techno.orderservice.rest.productclient.dto.response.ResWrapperProductDto;
import com.bootcamp.techno.orderservice.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final HttpServletRequest httpServletRequest;
    private final ProductClient productClient;

    @Override
    public APIResponse<String> addOrder(ReqOrderDto request) {
        String role = httpServletRequest.getHeader("role");
        if (!"CUSTOMER".equals(role)) {
            throw new RuntimeException("You don't have permission to create");
        }

        if (request.getIdProduct() == null || request.getTotalPrice() == null) {
            throw new IllegalArgumentException("Invalid order details");
        }

        ResponseEntity<ResWrapperProductDto> productResponse = productClient.getProductById(request.getIdProduct());
        if (productResponse != null && productResponse.getStatusCode().is2xxSuccessful()) {
            ResProductDto product = productResponse.getBody().getData();
            System.out.println(product);

            if (product == null || product.getId() == null) {
                return new APIResponse<>("Error: Product with ID " + request.getIdProduct() + " does not exist!");
            }

            if (!product.getPrice().equals(request.getTotalPrice())) {
                return new APIResponse<>("Error: Price mismatch for product ID " + request.getIdProduct());
            }
            if (product.getQuantity() <= 0) {
                return new APIResponse<>("Error: Product ID " + request.getIdProduct() + " is out of stock.");
            }

            OrderEntity entity = modelMapper.map(request, OrderEntity.class);
            entity.setIdUser(Long.valueOf(httpServletRequest.getHeader("idUser"))); // Menetapkan ID user dari header
            orderRepository.save(entity);

            return new APIResponse<>("Order Created Successfully");
        } else {
            return new APIResponse<>("Error: Failed to fetch product details.");
        }
    }


    @Override
    public APIResponse<String> updateOrder(Long id, ReqOrderDto request) {
        return null;
    }
}
