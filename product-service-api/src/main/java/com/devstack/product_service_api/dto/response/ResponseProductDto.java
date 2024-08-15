package com.devstack.product_service_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseProductDto {
    private String id;
    private String description;
    private double unitPrice;
    private int qtyOnHand;
}
