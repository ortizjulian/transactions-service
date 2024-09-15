package com.emazon.transactions.infrastructure.output.feign.mapper;

import com.emazon.transactions.domain.model.UpdateQuantity;
import com.emazon.transactions.infrastructure.output.feign.dto.UpdateQuantityFeignDto;
import com.emazon.transactions.utils.Constants;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = Constants.MAPPER_SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UpdateQuantityFeignMapper {

    UpdateQuantityFeignDto toFeignDto(UpdateQuantity updateQuantity);
}
