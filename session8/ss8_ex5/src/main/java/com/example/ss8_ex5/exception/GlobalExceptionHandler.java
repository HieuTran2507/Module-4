package com.example.ss8_ex5.exception;

import com.example.ss8_ex5.model.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
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
    public ErrorResponse handleMaxSize(
            MaxUploadSizeExceededException ex
    ) {

        return new ErrorResponse(
                413,
                "File quá lớn",
                LocalDateTime.now()
        );
    }

    // các lỗi khác
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(
            Exception ex
    ) {

        return new ErrorResponse(
                400,
                ex.getMessage(),
                LocalDateTime.now()
        );
    }
}
