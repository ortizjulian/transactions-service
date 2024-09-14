package com.emazon.transactions.domain.usecase;

import com.emazon.transactions.domain.api.ISupplyServicePort;
import com.emazon.transactions.domain.exceptions.ArticleNotFoundException;
import com.emazon.transactions.domain.exceptions.FeignClientUnexpectedResponseException;
import com.emazon.transactions.domain.model.StatusCodeEnum;
import com.emazon.transactions.domain.model.Supply;
import com.emazon.transactions.domain.model.UpdateQuantity;
import com.emazon.transactions.domain.spi.IArticlePersistencePort;
import com.emazon.transactions.domain.spi.ISecurityPersistencePort;
import com.emazon.transactions.domain.spi.ISupplyPersistencePort;
import com.emazon.transactions.utils.FeignConstants;


import java.time.LocalDateTime;

public class SupplyUseCase implements ISupplyServicePort {

    private final ISupplyPersistencePort supplyPersistencePort;
    private final IArticlePersistencePort articlePersistencePort;
    private final ISecurityPersistencePort securityPersistencePort;

    public SupplyUseCase(ISupplyPersistencePort supplyPersistencePort, IArticlePersistencePort articlePersistencePort, ISecurityPersistencePort securityPersistencePort) {
        this.supplyPersistencePort = supplyPersistencePort;
        this.articlePersistencePort = articlePersistencePort;
        this.securityPersistencePort = securityPersistencePort;
    }

    @Override
    public void addSupply(Supply supply, String token) {
        try{
            supply.setTransactionDate(LocalDateTime.now());

            securityPersistencePort.setToken(token);

            UpdateQuantity updateQuantity = new UpdateQuantity(supply.getArticleId(),supply.getQuantity());
            StatusCodeEnum statusCodeEnum = articlePersistencePort.updateArticleQuantity(updateQuantity);

            if(statusCodeEnum.equals(StatusCodeEnum.NOT_FOUND)) {
                throw new ArticleNotFoundException(FeignConstants.EXCEPTION_ARTICLE_NOT_FOUND_BY_ID +updateQuantity.getArticleId());
            }

            if (!statusCodeEnum.equals(StatusCodeEnum.NO_CONTENT)) {
                throw new FeignClientUnexpectedResponseException(FeignConstants.EXCEPTION_FEIGN_UNEXPECTED_RESPONSE + statusCodeEnum);
            }

            supplyPersistencePort.saveSupply(supply);

        } catch (Exception e) {
            securityPersistencePort.removeToken();
            throw e;
        }

    }
}
