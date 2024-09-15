package com.emazon.transactions.domain.model;

import java.time.LocalDateTime;

public class Supply {
    private LocalDateTime transactionDate;
    private Long articleId;
    private Integer quantity;
    private Long performedByUserId;

    public Supply(LocalDateTime transactionDate, Long articleId, Integer quantity, Long performedByUserId) {
        this.transactionDate = transactionDate;
        this.articleId = articleId;
        this.quantity = quantity;
        this.performedByUserId = performedByUserId;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getPerformedByUserId() {
        return performedByUserId;
    }

    public void setPerformedByUserId(Long performedByUserId) {
        this.performedByUserId = performedByUserId;
    }
}
