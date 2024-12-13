package com.techno.bootcamp.paymentservice.model;


import com.techno.bootcamp.paymentservice.util.Constant;
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

    public APIResponse(T data) {
        this.status = Constant.STATUS_SUCCES;
        this.data = data;
    }


}
