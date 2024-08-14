package com.devstack.user_service_api.dto.response;

import com.devstack.user_service_api.repo.BillingAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseSystemUserDto {
    private String email;
    private String firstName;
    private String lastName;
    private ResponseBillingAddress billingAddress;
}
