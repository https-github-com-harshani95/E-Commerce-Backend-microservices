package com.devstack.order_service_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order-service/api/v1/test")
public class TestController {
    @GetMapping
    public String test(){
        return "Order Service Running port 8083";
    }
}
