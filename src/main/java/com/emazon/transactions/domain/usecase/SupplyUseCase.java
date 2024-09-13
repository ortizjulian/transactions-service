package com.emazon.transactions.domain.usecase;

import com.emazon.transactions.domain.api.ISupplyServicePort;
import com.emazon.transactions.domain.model.Supply;
import com.emazon.transactions.domain.spi.ISupplyPersistencePort;

import java.time.LocalDateTime;

public class SupplyUseCase implements ISupplyServicePort {

    private final ISupplyPersistencePort supplyPersistencePort;

    public SupplyUseCase(ISupplyPersistencePort supplyPersistencePort) {
        this.supplyPersistencePort = supplyPersistencePort;
    }

    @Override
    public void addSupply(Supply supply) {
        supply.setTransactionDate(LocalDateTime.now());
        supply.setPerformedByUserId(1L);
        supplyPersistencePort.saveSupply(supply);
    }
}
