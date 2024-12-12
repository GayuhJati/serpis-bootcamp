package com.bootcamp.techno.orderservice.model;

import com.bootcamp.techno.orderservice.util.Constant;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class APIResponse<T> {
    private String status;
    private List<String> message = new ArrayList<>();
    private T data;
    public APIResponse(T data) {
        this.status = Constant.STATUS_PENDING;
        this.data = data;
    }
}
