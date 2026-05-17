package com.example.ss8_ex2.exception;

import com.example.ss8_ex2.model.dto.errorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobleHandlerException {
    @ExceptionHandler(ArithmeticException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public errorResponse handleArithmeticException(ArithmeticException ex) {
        return new errorResponse(
                400,
                ex.getMessage(),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public errorResponse handleTypeMismatch(MethodArgumentTypeMismatchException ex) {

        return new errorResponse(
                400,
                "Dữ liệu nhập vào phải là số",
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public errorResponse handleException(Exception ex) {

        return new errorResponse(
                500,
                "Lỗi hệ thống",
                LocalDateTime.now()
        );
    }
}
