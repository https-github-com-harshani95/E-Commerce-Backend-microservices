package com.devstack.order_service_api.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "payments")
public class Payment {
    private String id;
    private Date paidDate;
    private String orderId;
    private String transactionId;
    private double amount;
    private String currency;
}
