package com.emazon.transactions.infrastructure.output.jpa.repository;

import com.emazon.transactions.infrastructure.output.jpa.entity.SupplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISupplyRepository extends JpaRepository<SupplyEntity,Long> {}
