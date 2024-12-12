package com.bootcamp.techno.orderservice.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReqOrderDto {
    private Long idProduct;
    private Double totalPrice;
}
