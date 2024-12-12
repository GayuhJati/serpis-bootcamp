package com.techno.bootcamp.authservice.exception;

import com.techno.bootcamp.authservice.model.APIResponse;
import com.techno.bootcamp.authservice.util.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<APIResponse<Void>> handleValidationExceptions(MethodArgumentNotValidException exception){
    List<String> errors = exception.getBindingResult().getAllErrors().stream().map(error -> {
      if(error instanceof FieldError fieldError){
        return fieldError.getField() + ": "+ fieldError.getDefaultMessage();
      }
      return error.getDefaultMessage();
    }).collect(Collectors.toList());

    return new ResponseEntity<>(new APIResponse<>(Constant.STATUS_ERROR,errors), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(CustomException.class)
  public ResponseEntity<APIResponse<Void>> handleCustomException(CustomException e) {
    return new ResponseEntity<>(new APIResponse<>(e.getErrorStatus(), List.of(e.getMessage())), HttpStatus.BAD_REQUEST);
  }

}
