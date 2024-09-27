package com.emazon.transactions.infrastructure.output.feign.client;

import com.emazon.transactions.domain.model.SaleReport;
import com.emazon.transactions.infrastructure.configuration.feign.FeignClientConfig;
import com.emazon.transactions.utils.FeignConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = FeignConstants.FEIGN_REPORT_NAME, url =FeignConstants.PATH_REPORT_URL,configuration = FeignClientConfig.class)
public interface ReportFeignClient {

    @PostMapping(FeignConstants.PATH_CREATE_REPORT)
    ResponseEntity<Void> saveSaleReport(@RequestBody SaleReport saleReport);
}
