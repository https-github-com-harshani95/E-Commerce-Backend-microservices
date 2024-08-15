package com.devstack.order_service_api.repo;

import com.devstack.order_service_api.entity.CustomerOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerOrderRepo extends MongoRepository<CustomerOrder,String> {
}
