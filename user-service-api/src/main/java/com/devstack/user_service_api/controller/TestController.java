package com.devstack.user_service_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user-service/api/v1/test")
public class TestController {
    @GetMapping
    public String test(){
        return "User Service Running port 8081";
    }
}
