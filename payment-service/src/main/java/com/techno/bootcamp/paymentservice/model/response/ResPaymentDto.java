package com.techno.bootcamp.paymentservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResPaymentDto {
    private String status;
    private String message;
}
