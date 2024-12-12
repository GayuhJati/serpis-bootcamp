package com.bootcamp.techno.orderservice.model.request;

import lombok.Data;

@Data
public class ReqOrderDto {
    private Long idProduct;
    private Double totalPrice;
}
