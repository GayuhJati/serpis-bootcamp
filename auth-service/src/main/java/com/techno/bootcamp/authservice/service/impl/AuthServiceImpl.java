package com.techno.bootcamp.authservice.service.impl;

import com.techno.bootcamp.authservice.entity.UserEntity;
import com.techno.bootcamp.authservice.exception.CustomException;
import com.techno.bootcamp.authservice.model.APIResponse;
import com.techno.bootcamp.authservice.model.request.ReqEncodeUserJwtDto;
import com.techno.bootcamp.authservice.model.request.ReqLoginDto;
import com.techno.bootcamp.authservice.model.request.ReqRegistrationDto;
import com.techno.bootcamp.authservice.model.response.ResLoginDto;
import com.techno.bootcamp.authservice.model.response.ResUserDto;
import com.techno.bootcamp.authservice.repository.UserRepository;
import com.techno.bootcamp.authservice.service.AuthService;
import com.techno.bootcamp.authservice.util.Constant;
import com.techno.bootcamp.authservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtUtil jwtUtil;

    @Override
    public APIResponse<List<ResUserDto>> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();

//        List<ResUserDto> responseDto = new ArrayList<>();
//        for (UserEntity user : users) {
////            ResUserDto dto = ResUserDto.builder().firstname(user.getFirstName())
////                    .lastname(user.getLastName())
////                    .email(user.getEmail())
////                    .role(user.getRole())
////                    .build();
//            ResUserDto dto = modelMapper.map(user, ResUserDto.class);
//            responseDto.add(dto);
//        }

        List<ResUserDto> responseDto2 =  users.stream().map( user -> modelMapper.map(user, ResUserDto.class)).toList();
        return new APIResponse<>(responseDto2);
    }



    @Override
    public APIResponse<String> registration(ReqRegistrationDto request) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        Optional<UserEntity> checkEmail = userRepository.findByEmail(request.getEmail());

        if (checkEmail.isPresent()) {
            throw new CustomException("Email already exists", 400, Constant.STATUS_ERROR);
        }

        UserEntity entity = modelMapper.map(request, UserEntity.class);

        entity.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));

//        UserEntity entity = new UserEntity();
//        entity.setFirstName(request.getFirstname());
//        entity.setLastName(request.getLastname());
//        entity.setEmail(request.getEmail());
//        entity.setPassword(request.getPassword());

        entity.setRole(request.getRole() != null ? request.getRole() : Constant.ROLE_USER);

        userRepository.save(entity);
        return new APIResponse<>("User registered successfully");
    }

    @Override
    public APIResponse<ResLoginDto> loginUser(ReqLoginDto request) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (request.getEmail() == null || request.getEmail().isEmpty() || request.getPassword()== null || request.getPassword().isEmpty()) {
            throw new CustomException("Email and password are required", 400, Constant.STATUS_ERROR);
        }

        UserEntity checkEmail = userRepository.findByEmail(request.getEmail()).orElseThrow(()->new CustomException("Email not found",404, Constant.STATUS_ERROR));

        if (!passwordEncoder.matches(request.getPassword(), checkEmail.getPassword())) {
            throw new CustomException("Invalid password", 400, Constant.STATUS_ERROR);
        }

        ReqEncodeUserJwtDto reqToken = new ReqEncodeUserJwtDto();

        reqToken.setId(checkEmail.getId().toString());
        reqToken.setEmail(checkEmail.getEmail());
        reqToken.setRole(checkEmail.getRole());

        String token = jwtUtil.createUserJwt(reqToken);


        return new APIResponse<>(new ResLoginDto(token));
    }

    @Override
    public APIResponse<ResUserDto> getUserById(Long id) {
        Optional<UserEntity> checkUser = userRepository.findById(id);
        if (checkUser.isPresent()) {
            ResUserDto dto = modelMapper.map(checkUser.get(), ResUserDto.class);
            return new APIResponse<>(dto);
        } else {
            throw new CustomException("User " + id + "Not found", 400 , Constant.STATUS_ERROR);
        }
    }
}
