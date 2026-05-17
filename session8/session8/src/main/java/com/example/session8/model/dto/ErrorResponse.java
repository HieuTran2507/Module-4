package com.example.session8.model.dto;

import java.time.LocalDateTime;

public class ErrorResponse {
    private int status; // mã lỗi HTTP
    private String message; // nội dung lỗi
    private LocalDateTime timestamp; // thời gian xảy ra lỗi

    public ErrorResponse() {
    }

    public ErrorResponse(int status, String message, LocalDateTime timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
