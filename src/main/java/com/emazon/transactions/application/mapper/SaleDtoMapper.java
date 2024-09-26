package com.emazon.transactions.application.mapper;

import com.emazon.transactions.application.dto.SaleItemDto;
import com.emazon.transactions.domain.model.ArticleQuantity;
import com.emazon.transactions.utils.Constants;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = Constants.MAPPER_SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface SaleDtoMapper {
    List<ArticleQuantity> toSaleList(List<SaleItemDto> saleItemDtoList);
}
