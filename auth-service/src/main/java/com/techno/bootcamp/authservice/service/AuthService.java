package com.techno.bootcamp.authservice.service;

import com.techno.bootcamp.authservice.model.APIResponse;
import com.techno.bootcamp.authservice.model.request.ReqLoginDto;
import com.techno.bootcamp.authservice.model.request.ReqRegistrationDto;
import com.techno.bootcamp.authservice.model.response.ResLoginDto;
import com.techno.bootcamp.authservice.model.response.ResUserDto;

import java.util.List;

public interface AuthService {
    APIResponse<List<ResUserDto>> getAllUsers();
    APIResponse<String> registration(ReqRegistrationDto request);
    APIResponse<ResLoginDto> loginUser(ReqLoginDto request);
    APIResponse<ResUserDto> getUserById(Long id);
}
