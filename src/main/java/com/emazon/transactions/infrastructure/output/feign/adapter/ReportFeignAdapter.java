package com.emazon.transactions.infrastructure.output.feign.adapter;

import com.emazon.transactions.domain.model.SaleReport;
import com.emazon.transactions.domain.spi.IReportPersistencePort;
import com.emazon.transactions.infrastructure.output.feign.client.ReportFeignClient;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReportFeignAdapter implements IReportPersistencePort {

    private final ReportFeignClient reportFeignClient;

    @Override
    public void saveSaleReport(SaleReport saleReport) {
        reportFeignClient.saveSaleReport(saleReport);
    }
}
