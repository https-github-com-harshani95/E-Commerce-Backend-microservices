package com.devstack.product_service_api.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "products")
public class Product {
    @Id
    private String id;
    private String description;
    private double unitPrice;
    private int qtyOnHand;

}
