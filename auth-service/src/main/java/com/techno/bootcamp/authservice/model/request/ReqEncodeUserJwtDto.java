package com.techno.bootcamp.authservice.model.request;

import lombok.Data;

@Data
public class ReqEncodeUserJwtDto {
    private String id;
    private String email;
    private String role;
}
