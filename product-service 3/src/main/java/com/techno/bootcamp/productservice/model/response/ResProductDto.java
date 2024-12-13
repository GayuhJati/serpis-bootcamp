package com.techno.bootcamp.productservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResProductDto {
    private Long id;
    private String code;
    private String name;
    private Integer quantity;
    private String createdBy;
    private String updatedBy;
    private Double price;
}
