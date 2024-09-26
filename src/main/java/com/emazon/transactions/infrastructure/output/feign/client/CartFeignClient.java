package com.emazon.transactions.infrastructure.output.feign.client;

import com.emazon.transactions.infrastructure.configuration.feign.FeignClientConfig;
import com.emazon.transactions.utils.FeignConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = FeignConstants.FEIGN_CART_NAME, url =FeignConstants.PATH_CART_URL,configuration = FeignClientConfig.class)
public interface CartFeignClient {
    @DeleteMapping(value = FeignConstants.PATH_DELETE_CART_BY_ID)
    ResponseEntity<Void> removeArticleFromCart(@PathVariable(FeignConstants.ARTICLE_ID) Long articleId);
}