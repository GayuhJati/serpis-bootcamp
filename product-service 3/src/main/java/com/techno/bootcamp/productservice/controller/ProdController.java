package com.techno.bootcamp.productservice.controller;


import com.techno.bootcamp.productservice.model.APIResponse;
import com.techno.bootcamp.productservice.model.request.ReqProductDto;
import com.techno.bootcamp.productservice.model.response.ResProductDto;
import com.techno.bootcamp.productservice.service.ProdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/prod")
@RequiredArgsConstructor
public class ProdController {
    private final ProdService prodService;

    @GetMapping
    public ResponseEntity<APIResponse<List<ResProductDto>>> getAllUsers(){
        return ResponseEntity.ok(prodService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<ResProductDto>> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(prodService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<APIResponse<String>> createProduct(@RequestBody ReqProductDto request) {
        return ResponseEntity.ok(prodService.createProduct(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<String>> updateProduct(@PathVariable Long id, @RequestBody ReqProductDto request) {
        return ResponseEntity.ok(prodService.updateProduct(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> deleteProduct(@PathVariable Long id) {
        return ResponseEntity.ok(prodService.deleteProduct(id));
    }

}
