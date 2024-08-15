package com.devstack.user_service_api.service.impl;

import com.devstack.user_service_api.dto.request.RequestSystemUserDto;
import com.devstack.user_service_api.service.SystemUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SystemUserServiceImpl implements SystemUserService {
    @Override
    public void signup(RequestSystemUserDto requestSystemUserDto) {

    }

    @Override
    public void login(String email, String password) {

    }
}
