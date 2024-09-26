package com.emazon.transactions.application.handler;

import com.emazon.transactions.application.dto.SaleDtoRequest;
import com.emazon.transactions.application.mapper.SaleDtoMapper;
import com.emazon.transactions.domain.api.ISaleServicePort;
import com.emazon.transactions.domain.model.ArticleQuantity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SaleHandler implements ISaleHandler {

    private final ISaleServicePort saleServicePort;
    private final SaleDtoMapper saleDtoMapper;

    @Override
    public void createSale(SaleDtoRequest saleDtoRequest, Long userId) {
        List<ArticleQuantity> saleList = saleDtoMapper.toSaleList(saleDtoRequest.getItems());
        saleServicePort.createSale(saleList,userId);
    }
}
