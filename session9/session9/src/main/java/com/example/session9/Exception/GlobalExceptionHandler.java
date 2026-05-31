package com.example.session9.Exception;

import com.example.session9.model.DTO.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<Map<String,String>>> handleValidationException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(err -> errors.put(err.getField(),err.getDefaultMessage()));

        Response<Map<String,String>> response =
                Response.<Map<String,String>>builder()
                        .status("FAIL")
                        .message("Validation failed")
                        .data(errors)
                        .build();

        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

}
