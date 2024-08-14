package com.devstack.user_service_api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestBillingAddressDto {
    private String address;
    private String city;
    private String country;
    private String postalCode;
}
