package com.devstack.user_service_api.service;

import com.devstack.user_service_api.dto.request.RequestSystemUserDto;

public interface SystemUserService {
    public void signup(RequestSystemUserDto requestSystemUserDto);
    public Object login(String email,String password);
}
