package com.bootcamp.techno.orderservice.rest.productclient.dto.response;


import lombok.Data;

import java.util.List;

@Data
public class ResProductStructure<T> {
    private String status;
    private List<String> message;
    private T data;
}