package com.devstack.product_service_api.service.impl;

import com.amazonaws.services.accessanalyzer.model.InternalServerException;
import com.devstack.product_service_api.dto.request.RequestProductDto;
import com.devstack.product_service_api.dto.response.ResponseProductDto;
import com.devstack.product_service_api.dto.response.paginated.ResponseProductPaginateDto;
import com.devstack.product_service_api.entity.FileData;
import com.devstack.product_service_api.entity.Product;
import com.devstack.product_service_api.repo.ProductRepo;
import com.devstack.product_service_api.service.FileService;
import com.devstack.product_service_api.service.ProductService;
import com.devstack.product_service_api.util.CommonFileSavedBinaryDataDTO;
import com.devstack.product_service_api.util.FileDataExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Value("${bucketName}")
    private String bucketName;

    private final FileService fileService;
    private final FileDataExtractor fileDataExtractor;
    private final ProductRepo productRepo;

    @Override
    public void create(RequestProductDto requestProductDto, MultipartFile file) throws SQLException {
        CommonFileSavedBinaryDataDTO resource = null;
        try {

            resource = fileService.createResource(file, "products/icon/", bucketName);
            Product product= Product.builder()
                            .data(
                                    new FileData(
                                            fileDataExtractor.blobToByteArray(resource.getFileName()),
                                            fileDataExtractor.blobToByteArray(resource.getHash()),
                                            resource.getDirectory().getBytes(),
                                            fileDataExtractor.blobToByteArray(resource.getResourceUrl()))
                            )
                            .description(requestProductDto.getDescription())
                            .id(UUID.randomUUID().toString())
                            .qtyOnHand(requestProductDto.getQtyOnHand())
                            .unitPrice(requestProductDto.getUnitPrice())
                            .build();

            productRepo.save(product);

        } catch (Exception e) {
            assert resource != null;
            fileService.deleteResource(bucketName,
                    resource.getDirectory(), fileDataExtractor.extractActualFileName(
                            new InputStreamReader(
                                    resource.getFileName().getBinaryStream())));
            throw new InternalServerException("Something went wrong");
        }
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
