package com.emazon.transactions.application.dto;

import com.emazon.transactions.utils.Constants;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class SaleDtoRequest {
    @NotNull(message = Constants.EXCEPTION_SALE_ITEMS_NULL)
    @NotEmpty(message = Constants.EXCEPTION_SALE_ITEMS_EMPTY)
    private List<SaleItemDto> items;

    public SaleDtoRequest() {
    }

    public SaleDtoRequest(List<SaleItemDto> items) {
        this.items = items;
    }

    public @NotNull(message = Constants.EXCEPTION_SALE_ITEMS_NULL) @NotEmpty(message = Constants.EXCEPTION_SALE_ITEMS_EMPTY) List<SaleItemDto> getItems() {
        return items;
    }

    public void setItems(@NotNull(message = Constants.EXCEPTION_SALE_ITEMS_NULL) @NotEmpty(message = Constants.EXCEPTION_SALE_ITEMS_EMPTY) List<SaleItemDto> items) {
        this.items = items;
    }
}
