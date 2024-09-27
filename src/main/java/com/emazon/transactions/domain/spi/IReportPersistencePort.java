package com.emazon.transactions.domain.spi;

import com.emazon.transactions.domain.model.SaleReport;

public interface IReportPersistencePort {

    void saveSaleReport(SaleReport saleReport);
}
