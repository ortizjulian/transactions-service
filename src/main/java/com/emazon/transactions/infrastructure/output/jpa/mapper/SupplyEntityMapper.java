package com.emazon.transactions.infrastructure.output.jpa.mapper;

import com.emazon.transactions.domain.model.Supply;
import com.emazon.transactions.infrastructure.output.jpa.entity.SupplyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface SupplyEntityMapper {
    SupplyEntity toEntity(Supply supply);
}
