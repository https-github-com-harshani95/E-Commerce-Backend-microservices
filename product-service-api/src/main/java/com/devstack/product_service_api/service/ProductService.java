package com.devstack.product_service_api.service;

import com.devstack.product_service_api.dto.request.RequestProductDto;
import com.devstack.product_service_api.dto.response.ResponseProductDto;
import com.devstack.product_service_api.dto.response.paginated.ResponseProductPaginateDto;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;

public interface ProductService {
    public void create(RequestProductDto requestProductDto, MultipartFile file) throws SQLException;
    public void delete(String id);
    public void update(RequestProductDto requestProductDto, String id);
    public ResponseProductDto findById(String id);
    public ResponseProductPaginateDto findAll(String searchText, int page, int size);
}
