package com.devstack.product_service_api.controller;

import com.devstack.product_service_api.dto.request.RequestProductDto;
import com.devstack.product_service_api.service.ProductService;
import com.devstack.product_service_api.util.StandardResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;

@RestController
@RequestMapping("/product-service/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/admin/create")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<StandardResponse> create(
            @RequestParam("data") String data,
            @RequestParam("icon") MultipartFile file) throws JsonProcessingException, SQLException {

        ObjectMapper objectMapper = new ObjectMapper();
        RequestProductDto requestProductDto = objectMapper.readValue(data, RequestProductDto.class);


        productService.create(requestProductDto,file);
        return new ResponseEntity<>(
                new StandardResponse(201,
                        "product Saved!", null),
                HttpStatus.CREATED
        );
    }
}
