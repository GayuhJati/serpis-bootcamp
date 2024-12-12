package com.techno.bootcamp.authservice.model;


import com.techno.bootcamp.authservice.util.Constant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIResponse<T> {
    private String status;
    private List<String> message = new ArrayList<>();
    private T data;
    public APIResponse(T data) {
        this.status = Constant.STATUS_SUCCESS;
        this.data = data;
    }

    public APIResponse(String status, List<String> message) {
        this.status = status;
        this.message = message;
    }


}
