package com.devstack.product_service_api.dto.response.paginated;

import com.devstack.product_service_api.dto.response.ResponseProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseProductPaginateDto {
    private long count;
    private List<ResponseProductDto> dataList;
}
