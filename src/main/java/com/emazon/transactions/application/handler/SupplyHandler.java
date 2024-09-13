package com.emazon.transactions.application.handler;

import com.emazon.transactions.application.dto.SupplyRequestDto;
import com.emazon.transactions.application.mapper.SupplyDtoMapper;
import com.emazon.transactions.domain.api.ISupplyServicePort;
import com.emazon.transactions.domain.model.Supply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SupplyHandler implements ISupplyHandler {

    private final ISupplyServicePort supplyServicePort;
    private final SupplyDtoMapper supplyDtoMapper;

    @Override
    public void addStockToArticle(SupplyRequestDto supplyRequestDto) {
        Supply supply =supplyDtoMapper.toSupply(supplyRequestDto);
        supplyServicePort.addSupply(supply);
    }
}
