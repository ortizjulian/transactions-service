package com.emazon.transactions.domain.api;

import com.emazon.transactions.domain.model.Supply;

import java.time.LocalDateTime;

public interface ISupplyServicePort {
    void addSupply(Supply supply);
    LocalDateTime nextSupplyDate(Long articleId);
}
