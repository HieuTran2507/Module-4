package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// http://localhost:8080/
@Controller
public class demoControllerHTML {
    @GetMapping("/")
    public String testHTML(){
        return "index";
    }

}
