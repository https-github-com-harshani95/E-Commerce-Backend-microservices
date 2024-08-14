package com.devstack.user_service_api.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "system_user")
public class SystemUser {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(unique = true, nullable = false, length = 200)
    private String email;

    @Column(columnDefinition = "TINYINT",nullable = false)
    private boolean status;

    @OneToOne(mappedBy = "systemUser", fetch = FetchType.LAZY)
    private BillingAddress billingAddress;
}
