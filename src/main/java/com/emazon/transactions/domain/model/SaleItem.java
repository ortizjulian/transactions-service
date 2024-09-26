package com.emazon.transactions.domain.model;

public class SaleItem {
    private Long articleId;
    private Integer quantity;
    private Double price;

    public SaleItem(Long articleId, Integer quantity, Double price) {
        this.articleId = articleId;
        this.quantity = quantity;
        this.price = price;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
