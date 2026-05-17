package com.example.ss8_ex2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {
    @GetMapping("/devide")
    public double devide(@RequestParam double a, @RequestParam double b){
        if (b==0) throw new ArithmeticException("không thể chia cho 0");
        return a/b;
    }
}
