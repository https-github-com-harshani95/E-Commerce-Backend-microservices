package com.devstack.user_service_api.service.impl;

import com.devstack.user_service_api.dto.request.RequestBillingAddressDto;
import com.devstack.user_service_api.service.BillingAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BillingAddressServiceImpl implements BillingAddressService {
    @Override
    public void manage(RequestBillingAddressDto requestBillingAddressDto, String token) {

    }
}
