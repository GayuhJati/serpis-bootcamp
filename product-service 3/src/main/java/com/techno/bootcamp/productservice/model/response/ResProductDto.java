package com.techno.bootcamp.productservice.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResProductDto {
    private String code;
    private String name;
    private Integer quantity;
    private String createdBy;
    private String updatedBy;
    private Double price;
}
