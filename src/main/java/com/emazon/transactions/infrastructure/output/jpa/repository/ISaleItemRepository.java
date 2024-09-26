package com.emazon.transactions.infrastructure.output.jpa.repository;

import com.emazon.transactions.infrastructure.output.jpa.entity.SaleItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISaleItemRepository extends JpaRepository<SaleItemEntity, Long> {
}