package com.emazon.transactions.application.handler;

import com.emazon.transactions.application.dto.SupplyRequestDto;

public interface ISupplyHandler {
    void addStockToArticle(SupplyRequestDto supplyRequestDto, Long userId, String token);
}
