package com.code.user.service.exceptions;


import com.code.user.service.payload.ApiResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourcsNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourcsNotFoundException ex)
    {
            String message =ex.getMessage();
        ApiResponse response = ApiResponse.builder().message(message).success(true)
                    .status(HttpStatus.NOT_FOUND).build();

            return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
    }
}
