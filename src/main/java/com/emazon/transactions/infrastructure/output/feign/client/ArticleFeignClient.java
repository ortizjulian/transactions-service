package com.emazon.transactions.infrastructure.output.feign.client;

import com.emazon.transactions.infrastructure.configuration.feign.FeignClientConfig;
import com.emazon.transactions.infrastructure.output.feign.dto.UpdateQuantityFeignDto;
import com.emazon.transactions.utils.FeignConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = FeignConstants.FEIGN_ARTICLE_NAME, url =FeignConstants.PATH_ARTICLE_URL,configuration = FeignClientConfig.class)
public interface ArticleFeignClient {
    @PatchMapping(value = FeignConstants.PATH_ARTICLE_UPDATE_QUANTITY)
    ResponseEntity<Void> updateArticleQuantity(@RequestBody UpdateQuantityFeignDto updateQuantityFeignDto);

}
