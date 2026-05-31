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

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Response<Object>>
    handleNotFound(ResourceNotFoundException ex){

        Response<Object> response =
                Response.builder()
                        .status("FAIL")
                        .message(ex.getMessage())
                        .data(null)
                        .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<Response<Object>>
    handleDuplicate(DuplicateResourceException ex){

        Response<Object> response =
                Response.builder()
                        .status("FAIL")
                        .message(ex.getMessage())
                        .data(null)
                        .build();

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(response);
    }

    @ExceptionHandler(InvalidFileException.class)
    public ResponseEntity<Response<Object>>
    handleFileException(InvalidFileException ex){

        Response<Object> response =
                Response.builder()
                        .status("FAIL")
                        .message(ex.getMessage())
                        .data(null)
                        .build();

        return ResponseEntity.badRequest()
                .body(response);
    }
}
