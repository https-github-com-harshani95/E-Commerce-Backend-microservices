package com.devstack.user_service_api.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RequestUserLoginDto {
    private String username;
    private String password;
}
