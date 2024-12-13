package com.techno.bootcamp.paymentservice.rest.orderclient.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResWrapperOrderDto {
    private ResOrderDto data;
}
