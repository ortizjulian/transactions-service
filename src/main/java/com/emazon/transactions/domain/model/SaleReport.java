package com.emazon.transactions.domain.model;

import java.util.List;

public class SaleReport {
    private double totalCost;
    private List<SaleItem> purchasedItems;

    public SaleReport(double totalCost, List<SaleItem> purchasedItems) {
        this.totalCost = totalCost;
        this.purchasedItems = purchasedItems;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public List<SaleItem> getPurchasedItems() {
        return purchasedItems;
    }

    public void setPurchasedItems(List<SaleItem> purchasedItems) {
        this.purchasedItems = purchasedItems;
    }
}
