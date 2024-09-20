package com.emazon.transactions.domain.spi;

import com.emazon.transactions.domain.model.Supply;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ISupplyPersistencePort {
    void saveSupply(Supply supply);
    Optional<LocalDateTime> findLastSupplyDateByArticleId(Long articleId);
}
