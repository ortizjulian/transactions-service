package com.emazon.transactions.infrastructure.output.feign.mapper;

import com.emazon.transactions.domain.model.UpdateQuantity;
import com.emazon.transactions.infrastructure.output.feign.dto.UpdateQuantityFeignDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UpdateQuantityFeignMapper {

    UpdateQuantityFeignDto toFeignDto(UpdateQuantity updateQuantity);
}
