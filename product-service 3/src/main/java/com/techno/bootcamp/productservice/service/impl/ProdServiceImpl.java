package com.techno.bootcamp.productservice.service.impl;

import com.techno.bootcamp.productservice.entity.ProdEntity;
import com.techno.bootcamp.productservice.model.APIResponse;
import com.techno.bootcamp.productservice.model.request.ReqProductDto;
import com.techno.bootcamp.productservice.model.response.ResProductDto;
import com.techno.bootcamp.productservice.repository.ProdRepository;
import com.techno.bootcamp.productservice.rest.authclient.AuthClient;
import com.techno.bootcamp.productservice.rest.authclient.dto.response.ResUserDto;
import com.techno.bootcamp.productservice.rest.authclient.dto.response.ResWrapperDto;
import com.techno.bootcamp.productservice.service.ProdService;
import com.techno.bootcamp.productservice.util.Constant;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdServiceImpl implements ProdService {
    private final ProdRepository prodRepository;
    private final AuthClient authClient;
    private final ModelMapper modelMapper;
    private final HttpServletRequest httpServletRequest;

    @Override
    public APIResponse<List<ResProductDto>> getAllProducts() {
        List<ProdEntity> products = prodRepository.findAll();

        List<ResProductDto> responseDto = products.stream().map(product -> {
            ResProductDto dto = modelMapper.map(product, ResProductDto.class);
            System.out.println("CreatedBy: " + product.getCreatedBy());
            try {
                ResponseEntity<ResWrapperDto> userResponse = authClient.getUserById(product.getCreatedBy());
                if (userResponse != null && userResponse.getStatusCode().is2xxSuccessful() && userResponse.getBody() != null) {
                    ResUserDto currentUser = userResponse.getBody().getData();
                    System.out.println("CreatedBy: " + currentUser.getFirstname());
                    dto.setCreatedBy(currentUser.getFirstname() + " " + currentUser.getLastname());
                } else {
                    dto.setCreatedBy("ERR: Failed to fetch user");
                }
            } catch (Exception e) {
                e.printStackTrace();
                dto.setCreatedBy("ERR: Failed to fetch user");
            }
            return dto;
        }).collect(Collectors.toList());

        return new APIResponse<>(responseDto, HttpStatus.OK);
    }


    @Override
    public APIResponse<ResProductDto> getProductById(Long id) {
        ProdEntity product = prodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        ResponseEntity<ResWrapperDto> userResponse = authClient.getUserById(product.getCreatedBy());
        ResProductDto resProductDto = ResProductDto.builder().id(product.getId()).code(product.getCode()).name(product.getName()).quantity(product.getQuantity()).price(product.getPrice()).createdBy(Objects.requireNonNull(userResponse.getBody()).getData().getFirstname()).updatedBy(userResponse.getBody().getData().getFirstname()).build();


        return new APIResponse<>(resProductDto, HttpStatus.OK);
    }

    @Override
    public APIResponse<String> createProduct(ReqProductDto request) {
        String role = httpServletRequest.getHeader("role");
        if (!"ADMIN".equals(role)) {
            throw new RuntimeException("You don't have permission to create");
        }
        ProdEntity entity = modelMapper.map(request, ProdEntity.class);
        entity.setCreatedBy(Long.valueOf(httpServletRequest.getHeader("idUser")));
        entity.setUpdatedBy(Long.valueOf(httpServletRequest.getHeader("idUser")));

//        ProdEntity prodEntity = new ProdEntity();
//        prodEntity.setCode(request.getCode());
//        prodEntity.setName(request.getName());
//        prodEntity.setQuantity(request.getQuantity());
//        prodEntity.setPrice(request.getPrice());

        prodRepository.save(entity);
        return new APIResponse<>("Success Saving", HttpStatus.OK);
    }

    @Override
    public APIResponse<String> updateProduct(Long id, ReqProductDto request) {
        ProdEntity product = prodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        String role = httpServletRequest.getHeader("role");
        if (!"ADMIN".equals(role)) {
            throw new RuntimeException("You don't have permission to create");
        }
        product.setCode(request.getCode());
        product.setName(request.getName());
        product.setQuantity(request.getQuantity());
        product.setPrice(request.getPrice());
        prodRepository.save(product);
        return new APIResponse<>("Product saved successfully", HttpStatus.OK);
    }

    @Override
    public APIResponse<String> deleteProduct(Long id) {
        String role = httpServletRequest.getHeader("role");
        if (!"ADMIN".equals(role)) {
            throw new RuntimeException("You don't have permission to create");
        }
        if (!prodRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        prodRepository.deleteById(id);
        return new APIResponse<>("Product deleted successfully", null);
    }
}
