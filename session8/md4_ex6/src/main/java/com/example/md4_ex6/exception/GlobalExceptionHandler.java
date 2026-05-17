package com.example.md4_ex6.exception;

import org.springframework.http.HttpStatus;
import com.example.md4_ex6.model.dto.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // file quá lớn
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
    public ErrorResponse handleMaxSize() {
        return new ErrorResponse(
                413,
                "File quá lớn",
                LocalDateTime.now()
        );
    }

    // validate DTO (@Email, @NotBlank)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidation(MethodArgumentNotValidException ex) {

        String msg = ex.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();

        return new ErrorResponse(
                400,
                msg,
                LocalDateTime.now()
        );
    }

    // lỗi runtime (file sai, logic sai)
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleOther(Exception ex) {

        return new ErrorResponse(
                400,
                ex.getMessage(),
                LocalDateTime.now()
        );
    }
}
