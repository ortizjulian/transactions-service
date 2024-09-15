package com.emazon.transactions.application.dto;

import com.emazon.transactions.utils.Constants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class SupplyRequestDto {

    @NotNull(message = Constants.EXCEPTION_ARTICLE_ID_NULL)
    @Positive(message = Constants.EXCEPTION_ARTICLE_ID_POSITIVE)
    private Long articleId;

    @NotNull(message = Constants.EXCEPTION_ARTICLE_QUANTITY_NULL)
    @Positive(message = Constants.EXCEPTION_ARTICLE_QUANTITY_POSITIVE)
    private Long quantity;

    public SupplyRequestDto(Long articleId, Long quantity) {

        this.articleId = articleId;
        this.quantity = quantity;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
