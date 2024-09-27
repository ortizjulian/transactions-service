package com.emazon.transactions.infrastructure.output.jpa.adapter;

import com.emazon.transactions.domain.model.Sale;
import com.emazon.transactions.domain.model.SaleItem;
import com.emazon.transactions.domain.spi.ISalePersistencePort;
import com.emazon.transactions.infrastructure.output.jpa.entity.SaleEntity;
import com.emazon.transactions.infrastructure.output.jpa.entity.SaleItemEntity;
import com.emazon.transactions.infrastructure.output.jpa.mapper.SaleEntityMapper;
import com.emazon.transactions.infrastructure.output.jpa.repository.ISaleItemRepository;
import com.emazon.transactions.infrastructure.output.jpa.repository.ISaleRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class SaleJpaAdapter implements ISalePersistencePort {

    private final ISaleRepository saleRepository;
    private final ISaleItemRepository saleItemRepository;
    private final SaleEntityMapper saleEntityMapper;

    @Override
    public Sale initializeSale(Sale sale) {
        SaleEntity saleEntity = saleEntityMapper.toSaleEntity(sale);

        SaleEntity savedSaleEntity = saleRepository.save(saleEntity);
        List<SaleItemEntity> saleItemEntities = new ArrayList<>();

        for (SaleItem item : sale.getItems()) {
            SaleItemEntity saleItemEntity = new SaleItemEntity();
            saleItemEntity.setSale(savedSaleEntity);
            saleItemEntity.setArticleId(item.getArticleId());
            saleItemEntity.setPrice(item.getPrice());
            saleItemEntity.setQuantity(item.getQuantity());

            saleItemEntities.add(saleItemEntity);
        }

        saleItemRepository.saveAll(saleItemEntities);
        savedSaleEntity.setItems(saleItemEntities);
        return saleEntityMapper.toSale(savedSaleEntity);
    }

    @Override
    public void updateSale(Sale sale) {
        SaleEntity saleEntity = saleEntityMapper.toSaleEntity(sale);
        saleRepository.save(saleEntity);
    }
}
