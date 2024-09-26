package com.emazon.transactions.infrastructure.output.jpa.mapper;

import com.emazon.transactions.domain.model.Sale;
import com.emazon.transactions.infrastructure.output.jpa.entity.SaleEntity;
import com.emazon.transactions.utils.Constants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = Constants.MAPPER_SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface SaleEntityMapper {
    Sale toSale(SaleEntity saleEntity);


    @Mapping(target = Constants.MAPPER_SALE_ENTITY_IGNORE_ITEMS, ignore = true)
    SaleEntity toSaleEntity(Sale sale);
}
