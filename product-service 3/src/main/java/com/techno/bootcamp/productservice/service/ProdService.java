package com.techno.bootcamp.productservice.service;

import com.techno.bootcamp.productservice.model.APIResponse;
import com.techno.bootcamp.productservice.model.request.ReqProductDto;
import com.techno.bootcamp.productservice.model.response.ResProductDto;

import java.util.List;

public interface ProdService {
    APIResponse<List<ResProductDto>> getAllProducts();
    APIResponse<ResProductDto> getProductById(Long id);
    APIResponse<String> createProduct(ReqProductDto request);
    APIResponse<String> updateProduct(Long id, ReqProductDto request);
    APIResponse<String> deleteProduct(Long id);
}
