package com.emazon.transactions.infrastructure.output.jpa.repository;

import com.emazon.transactions.infrastructure.output.jpa.entity.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISaleRepository extends JpaRepository<SaleEntity,Long> {
}
