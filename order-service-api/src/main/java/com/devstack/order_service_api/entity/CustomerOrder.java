package com.devstack.order_service_api.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "orders")
public class CustomerOrder {
    private String id;
    private List<Object> items;
    private double cost;
    private String customerEmail;
}
