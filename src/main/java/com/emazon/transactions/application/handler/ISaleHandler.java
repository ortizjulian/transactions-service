package com.emazon.transactions.application.handler;

import com.emazon.transactions.application.dto.SaleDtoRequest;

public interface ISaleHandler {
    void createSale(SaleDtoRequest saleDtoRequest, Long userId);
}
