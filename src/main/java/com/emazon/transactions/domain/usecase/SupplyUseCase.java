package com.emazon.transactions.domain.usecase;

import com.emazon.transactions.domain.api.ISupplyServicePort;
import com.emazon.transactions.domain.model.Supply;
import com.emazon.transactions.domain.model.UpdateQuantity;
import com.emazon.transactions.domain.spi.IArticlePersistencePort;
import com.emazon.transactions.domain.spi.ISupplyPersistencePort;


import java.time.LocalDateTime;

public class SupplyUseCase implements ISupplyServicePort {

    private ISupplyPersistencePort supplyPersistencePort;
    private IArticlePersistencePort articlePersistencePort;

    public SupplyUseCase(ISupplyPersistencePort supplyPersistencePort, IArticlePersistencePort articlePersistencePort) {
        this.supplyPersistencePort = supplyPersistencePort;
        this.articlePersistencePort = articlePersistencePort;
    }

    @Override
    public void addSupply(Supply supply) {
        supply.setTransactionDate(LocalDateTime.now());
        UpdateQuantity updateQuantity = new UpdateQuantity(supply.getArticleId(),supply.getQuantity());
        articlePersistencePort.updateArticleQuantity(updateQuantity);
        supplyPersistencePort.saveSupply(supply);
    }
}
