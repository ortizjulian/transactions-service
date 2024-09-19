package com.emazon.transactions.infrastructure.output.jpa.adapter;

import com.emazon.transactions.domain.model.Supply;
import com.emazon.transactions.domain.spi.ISupplyPersistencePort;
import com.emazon.transactions.infrastructure.output.jpa.entity.SupplyEntity;
import com.emazon.transactions.infrastructure.output.jpa.mapper.SupplyEntityMapper;
import com.emazon.transactions.infrastructure.output.jpa.repository.ISupplyRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SupplyJpaAdapter implements ISupplyPersistencePort {

    private final ISupplyRepository supplyRepository;
    private final SupplyEntityMapper supplyEntityMapper;

    @Override
    public void saveSupply(Supply supply) {
        SupplyEntity supplyEntity = supplyEntityMapper.toEntity(supply);
        supplyRepository.save(supplyEntity);
    }
}
