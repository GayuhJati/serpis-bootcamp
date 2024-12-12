package com.techno.bootcamp.productservice.model;

import com.techno.bootcamp.productservice.util.Constant;
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
    private HttpStatus httpStatus;


    public APIResponse(T data,HttpStatus httpStatus) {
        this.status = Constant.STATUS_SUCCESS;
        this.data = data;
        this.httpStatus = httpStatus;
    }

    public APIResponse(String message, HttpStatus httpStatus) {
        this.status = Constant.STATUS_SUCCESS;
        this.message.add(message);
        this.httpStatus = httpStatus;
    }

}
