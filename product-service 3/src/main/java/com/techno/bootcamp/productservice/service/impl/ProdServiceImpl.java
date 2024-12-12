package com.techno.bootcamp.productservice.service.impl;

import com.techno.bootcamp.productservice.entity.ProdEntity;
import com.techno.bootcamp.productservice.model.APIResponse;
import com.techno.bootcamp.productservice.model.request.ReqProductDto;
import com.techno.bootcamp.productservice.model.response.ResProductDto;
import com.techno.bootcamp.productservice.repository.ProdRepository;
import com.techno.bootcamp.productservice.rest.authclient.AuthClient;
import com.techno.bootcamp.productservice.rest.authclient.dto.response.ResUserDto;
import com.techno.bootcamp.productservice.service.ProdService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
            try {
                ResponseEntity<ResUserDto> userResponse = authClient.getUserById(product.getCreatedBy());
                if (userResponse != null && userResponse.getBody() != null) {
                    ResUserDto currentUser = userResponse.getBody();
                    dto.setCreatedBy(currentUser.getFirstname() + " " + currentUser.getLastname());
                } else {
                    dto.setCreatedBy("ERR: No Body");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return dto;
        }).toList();
        return new APIResponse<>(responseDto);
    }

    @Override
    public APIResponse<ResProductDto> getProductById(Long id) {
        ProdEntity product = prodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        ResponseEntity<ResUserDto> userResponse = authClient.getUserById(product.getCreatedBy());
        ResProductDto resProductDto = ResProductDto.builder().code(product.getCode()).name(product.getName()).quantity(product.getQuantity()).price(product.getPrice()).createdBy(userResponse.toString()).updatedBy(userResponse.toString()).build();


        return new APIResponse<>(resProductDto);
    }

    @Override
    public APIResponse<String> createProduct(ReqProductDto request) {

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
        product.setCode(request.getCode());
        product.setName(request.getName());
        product.setQuantity(request.getQuantity());
        product.setPrice(request.getPrice());
        prodRepository.save(product);
        return new APIResponse<>("Product saved successfully",HttpStatus.OK);
    }

    @Override
    public APIResponse<String> deleteProduct(Long id) {
        if (!prodRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        prodRepository.deleteById(id);
        return new APIResponse<>("Product deleted successfully", null);
    }
}
