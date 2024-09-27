package com.emazon.transactions.domain.spi;

import com.emazon.transactions.domain.model.Sale;

public interface ISalePersistencePort {
    Sale initializeSale(Sale sale);
    void updateSale(Sale sale);
}
