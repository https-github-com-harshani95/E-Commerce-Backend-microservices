package com.devstack.user_service_api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestSystemUserDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
