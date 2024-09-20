package com.emazon.transactions.application.handler;

import com.emazon.transactions.application.dto.SupplyRequestDto;

import java.time.LocalDateTime;

public interface ISupplyHandler {
    void addStockToArticle(SupplyRequestDto supplyRequestDto, Long userId);
    LocalDateTime nextSupplyDate(Long articleId);
}
