package com.techno.bootcamp.productservice.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReqProductDto {
    private String code;
    private String name;
    private Integer quantity;
    private Double price;
}
