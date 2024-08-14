package com.devstack.user_service_api.service;

import com.devstack.user_service_api.dto.request.RequestBillingAddressDto;

public interface BillingAddressService {
    public void manage(RequestBillingAddressDto requestBillingAddressDto, String token);
}
