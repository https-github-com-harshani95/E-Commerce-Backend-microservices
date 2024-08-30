package com.devstack.product_service_api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FileData {
    private Object fileName;
    private Object hash;
    private Object directory;
    private Object resourceUrl;
}
