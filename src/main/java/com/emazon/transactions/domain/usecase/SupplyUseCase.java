package com.emazon.transactions.domain.usecase;

import com.emazon.transactions.domain.api.ISupplyServicePort;
import com.emazon.transactions.domain.model.ArticleQuantity;
import com.emazon.transactions.domain.model.Supply;
import com.emazon.transactions.domain.spi.IArticlePersistencePort;
import com.emazon.transactions.domain.spi.ISupplyPersistencePort;
import com.emazon.transactions.utils.Constants;


import java.time.LocalDateTime;
import java.util.Optional;

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
        ArticleQuantity articleQuantity = new ArticleQuantity(supply.getArticleId(),supply.getQuantity());
        articlePersistencePort.updateArticleQuantity(articleQuantity);
        supplyPersistencePort.saveSupply(supply);
    }

    @Override
    public LocalDateTime nextSupplyDate(Long articleId) {
        Optional<LocalDateTime> lastSupplyDate = supplyPersistencePort.findLastSupplyDateByArticleId(articleId);
        LocalDateTime nextDate;
        if (lastSupplyDate.isEmpty()) {
            return LocalDateTime.now().plusMonths(Constants.ONE_MONTH);
        }
        nextDate = lastSupplyDate.get().plusMonths(Constants.ONE_MONTH);
        return nextDate;
    }
}
