package com.techno.bootcamp.authservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResUserDto {
    private String firstname;
    private String lastname;
    private String email;
    private String role;
}
