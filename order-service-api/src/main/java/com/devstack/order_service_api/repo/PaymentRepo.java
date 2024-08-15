package com.devstack.order_service_api.repo;

import com.devstack.order_service_api.entity.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepo extends MongoRepository<Payment,String> {
}
