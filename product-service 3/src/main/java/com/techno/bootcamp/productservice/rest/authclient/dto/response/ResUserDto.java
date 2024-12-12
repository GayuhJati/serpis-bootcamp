package com.techno.bootcamp.productservice.rest.authclient.dto.response;

import lombok.Data;

@Data
public class ResUserDto {
    private String firstname;
    private String lastname;
    private String email;
    private String role;
}
