package com.techno.bootcamp.authservice.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data

public class ReqRegistrationDto {
    @NotNull(message = "Firstname cannot be null")
    @NotEmpty
    private String firstname;

    @NotNull(message = "Lastname cannot be null")
    @NotEmpty
    private String lastname;

    @NotNull(message = "Email cannot be null")
    @NotEmpty
    @Email(regexp = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,3}")
    private String email;

    @NotEmpty
    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Password must be at least 8")
    private String password;

    private String role;
}
