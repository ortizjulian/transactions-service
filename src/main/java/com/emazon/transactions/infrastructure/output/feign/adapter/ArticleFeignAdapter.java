package com.emazon.transactions.infrastructure.output.feign.adapter;

import com.emazon.transactions.domain.model.StatusCodeEnum;
import com.emazon.transactions.domain.model.UpdateQuantity;
import com.emazon.transactions.domain.spi.IArticlePersistencePort;
import com.emazon.transactions.infrastructure.output.feign.client.ArticleFeignClient;
import com.emazon.transactions.infrastructure.output.feign.dto.UpdateQuantityFeignDto;
import com.emazon.transactions.infrastructure.output.feign.mapper.UpdateQuantityFeignMapper;
import com.emazon.transactions.infrastructure.output.feign.utils.FeignExceptionHandler;
import feign.FeignException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ArticleFeignAdapter implements IArticlePersistencePort {

    private final ArticleFeignClient articleFeignClient;
    private final UpdateQuantityFeignMapper updateQuantityFeignMapper;

    @Override
    public StatusCodeEnum updateArticleQuantity(UpdateQuantity updateQuantity) {
        UpdateQuantityFeignDto updateQuantityFeignDto = updateQuantityFeignMapper.toFeignDto(updateQuantity);

        try {
            articleFeignClient.updateArticleQuantity(updateQuantityFeignDto);
            return StatusCodeEnum.NO_CONTENT;
        } catch (FeignException e) {
            return FeignExceptionHandler.handleFeignException(e);
        } catch (Exception e) {
            return StatusCodeEnum.INTERNAL_SERVER_ERROR;
        }
    }
}
