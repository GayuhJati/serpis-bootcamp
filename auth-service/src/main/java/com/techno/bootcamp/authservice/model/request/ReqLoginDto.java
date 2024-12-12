package com.techno.bootcamp.authservice.model.request;


import lombok.Data;

@Data
public class ReqLoginDto {
    private String email;
    private String password;
}
