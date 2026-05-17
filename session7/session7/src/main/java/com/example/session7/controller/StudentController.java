package com.example.session7.controller;

import com.example.session7.model.dto.StudentDTO;
import com.example.session7.responseAPI.APIResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/student")
public class StudentController {
    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody @Valid StudentDTO s, BindingResult br){
        if (br.hasErrors()){
            String errorMessage = Objects.requireNonNull(br.getFieldError()).getDefaultMessage();
            APIResponse errResponse = new APIResponse(errorMessage);
            //return ResponseEntity.badRequest().body(br.getAllErrors());
            return ResponseEntity.badRequest().body(errResponse);
        }

        return ResponseEntity.ok("thêm sinh viên thành công");
    }
}
