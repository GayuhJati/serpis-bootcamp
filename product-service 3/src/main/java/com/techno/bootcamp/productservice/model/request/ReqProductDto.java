package com.techno.bootcamp.productservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReqProductDto {
    private String code;
    private String name;
    private Integer quantity;
    private Double price;
}
