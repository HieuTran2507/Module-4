package com.example.session9.model.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Response<T> {
    private String status;
    private String message;
    private T data;
}
