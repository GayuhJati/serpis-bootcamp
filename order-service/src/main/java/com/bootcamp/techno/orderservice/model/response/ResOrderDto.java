package com.bootcamp.techno.orderservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResOrderDto {
    private Long id;
    private Double totalPrice;
}
