package com.devstack.product_service_api.repo;

import com.devstack.product_service_api.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepo extends MongoRepository<Product,String> {
}
