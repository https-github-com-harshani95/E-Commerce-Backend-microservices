package com.devstack.user_service_api.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "billing_address")
public class BillingAddress {
    @Id
    @Column(name = "address_id")
    private String addressId;
    private String address;
    private String city;
    private String country;
    private String postalCode;

    @OneToOne
    @JoinColumn(name = "user_id")
    private SystemUser systemUser;
}
