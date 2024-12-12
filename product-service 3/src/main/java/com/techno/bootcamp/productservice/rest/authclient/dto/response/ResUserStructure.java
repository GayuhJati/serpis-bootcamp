package com.techno.bootcamp.productservice.rest.authclient.dto.response;


import lombok.Data;

import java.util.List;

@Data
public class ResUserStructure<T> {
    private String status;
    private List<String> message;
    private T data;
}
