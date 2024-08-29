package com.devstack.user_service_api.controller;

import com.devstack.user_service_api.dto.request.RequestSystemUserDto;
import com.devstack.user_service_api.service.SystemUserService;
import com.devstack.user_service_api.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("user-service/api/v1/users")
public class SystemUserController {

    private final SystemUserService systemUserService;

    @PostMapping("/signup")
    public ResponseEntity<StandardResponse> signup(@RequestBody RequestSystemUserDto requestSystemUserDto) {
        systemUserService.signup(requestSystemUserDto);
        return new ResponseEntity<>(
                new StandardResponse(201, "User registered..", null),
                HttpStatus.CREATED
        );
    }
}
