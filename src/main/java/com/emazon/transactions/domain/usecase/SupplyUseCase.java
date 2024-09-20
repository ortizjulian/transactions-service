package com.emazon.transactions.domain.usecase;

import com.emazon.transactions.domain.api.ISupplyServicePort;
import com.emazon.transactions.domain.model.Supply;
import com.emazon.transactions.domain.model.UpdateQuantity;
import com.emazon.transactions.domain.spi.IArticlePersistencePort;
import com.emazon.transactions.domain.spi.ISecurityPersistencePort;
import com.emazon.transactions.domain.spi.ISupplyPersistencePort;


import java.time.LocalDateTime;

public class SupplyUseCase implements ISupplyServicePort {

    private ISupplyPersistencePort supplyPersistencePort;
    private IArticlePersistencePort articlePersistencePort;
    private ISecurityPersistencePort securityPersistencePort;

    public SupplyUseCase(ISupplyPersistencePort supplyPersistencePort, IArticlePersistencePort articlePersistencePort, ISecurityPersistencePort securityPersistencePort) {
        this.supplyPersistencePort = supplyPersistencePort;
        this.articlePersistencePort = articlePersistencePort;
        this.securityPersistencePort = securityPersistencePort;
    }

    @Override
    public void addSupply(Supply supply, String token) {
        try{
            supply.setTransactionDate(LocalDateTime.now());

            securityPersistencePort.setToken(token);

            UpdateQuantity updateQuantity = new UpdateQuantity(supply.getArticleId(),supply.getQuantity());
            articlePersistencePort.updateArticleQuantity(updateQuantity);

            supplyPersistencePort.saveSupply(supply);

        } catch (Exception e) {
            securityPersistencePort.removeToken();
            throw e;
        }
    }
}
