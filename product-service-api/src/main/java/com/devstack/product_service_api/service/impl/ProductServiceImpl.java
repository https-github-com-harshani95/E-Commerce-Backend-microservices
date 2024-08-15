package com.devstack.product_service_api.service.impl;

import com.devstack.product_service_api.dto.request.RequestProductDto;
import com.devstack.product_service_api.dto.response.ResponseProductDto;
import com.devstack.product_service_api.dto.response.paginated.ResponseProductPaginateDto;
import com.devstack.product_service_api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    @Override
    public void create(RequestProductDto requestProductDto) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(RequestProductDto requestProductDto, String id) {

    }

    @Override
    public ResponseProductDto findById(String id) {
        return null;
    }

    @Override
    public ResponseProductPaginateDto findAll(String searchText, int page, int size) {
        return null;
    }
}
