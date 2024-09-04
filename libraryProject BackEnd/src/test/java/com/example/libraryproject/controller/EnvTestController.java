package com.example.libraryproject.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvTestController {
    @Value("${MY_APP_NAME:NOT_SET}")
    private String appName;

    @GetMapping("/test-env")
    public String getAppName() {
        return "App Name: " + appName;
    }
}
