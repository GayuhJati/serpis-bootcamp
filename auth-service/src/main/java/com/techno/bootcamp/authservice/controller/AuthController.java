package com.techno.bootcamp.authservice.controller;

import com.techno.bootcamp.authservice.model.APIResponse;
import com.techno.bootcamp.authservice.model.request.ReqLoginDto;
import com.techno.bootcamp.authservice.model.request.ReqRegistrationDto;
import com.techno.bootcamp.authservice.model.response.ResLoginDto;
import com.techno.bootcamp.authservice.model.response.ResUserDto;
import com.techno.bootcamp.authservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/users")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping
    public ResponseEntity<APIResponse<List<ResUserDto>>> getAllUsers(){
        return ResponseEntity.ok(authService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<ResUserDto>> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(authService.getUserById(id));
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse<ResLoginDto>> login(@RequestBody ReqLoginDto request){
        return ResponseEntity.ok(authService.loginUser(request));
    }

    @PostMapping("/register")
    public ResponseEntity<APIResponse<String>> registration(@Valid @RequestBody ReqRegistrationDto request){
        return new ResponseEntity<>(authService.registration(request), HttpStatus.CREATED);
    }
}
