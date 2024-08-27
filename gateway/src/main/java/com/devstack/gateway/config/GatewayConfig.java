package com.devstack.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
                .route("user-service-api",r-> r.path("/user-service/**")
                        .uri("http://localhost:8081"))
                .route("product-service-api",r-> r.path("/product-service/**")
                        .uri("http://localhost:8082"))
                .route("order-service-api",r-> r.path("/order-service/**")
                        .uri("http://localhost:8083"))
                .build();
    }
}
