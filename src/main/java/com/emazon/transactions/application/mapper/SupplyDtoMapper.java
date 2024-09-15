package com.emazon.transactions.application.mapper;

import com.emazon.transactions.application.dto.SupplyRequestDto;
import com.emazon.transactions.domain.model.Supply;
import com.emazon.transactions.utils.Constants;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = Constants.MAPPER_SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface SupplyDtoMapper {
    Supply toSupply(SupplyRequestDto supplyRequestDto);
}
