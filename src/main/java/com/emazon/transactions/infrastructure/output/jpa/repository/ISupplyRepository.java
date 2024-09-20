package com.emazon.transactions.infrastructure.output.jpa.repository;

import com.emazon.transactions.infrastructure.output.jpa.entity.SupplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ISupplyRepository extends JpaRepository<SupplyEntity,Long> {
    @Query("SELECT s.transactionDate FROM SupplyEntity s WHERE s.articleId = :articleId ORDER BY s.transactionDate DESC LIMIT 1")
    Optional<LocalDateTime> findLastSupplyDateByArticleId(Long articleId);
}
