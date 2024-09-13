package com.emazon.transactions.domain.spi;

import com.emazon.transactions.domain.model.Supply;

public interface ISupplyPersistencePort {
    void saveSupply(Supply supply);
}
