package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secure")
public class SecureDataController {

    @GetMapping("/data")
    public String getSecureData() {
        return "This is protected data. You have a valid JWT token.";
    }
}