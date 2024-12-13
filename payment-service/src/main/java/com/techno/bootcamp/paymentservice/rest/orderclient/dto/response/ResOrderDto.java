package com.techno.bootcamp.paymentservice.rest.orderclient.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResOrderDto {
    private Long orderId;
    private Double orderTotal;
}
