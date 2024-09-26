package com.emazon.transactions.domain.usecase;

import com.emazon.transactions.domain.api.ISaleServicePort;
import com.emazon.transactions.domain.exceptions.SaleCreationException;
import com.emazon.transactions.domain.model.Article;
import com.emazon.transactions.domain.model.ArticleQuantity;
import com.emazon.transactions.domain.model.Sale;
import com.emazon.transactions.domain.model.SaleItem;
import com.emazon.transactions.domain.spi.IArticlePersistencePort;
import com.emazon.transactions.domain.spi.ISalePersistencePort;
import com.emazon.transactions.utils.Constants;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SaleUseCase implements ISaleServicePort {

    private IArticlePersistencePort articlePersistencePort;
    private ISalePersistencePort salePersistencePort;

    public SaleUseCase(IArticlePersistencePort articlePersistencePort, ISalePersistencePort salePersistencePort) {
        this.articlePersistencePort = articlePersistencePort;
        this.salePersistencePort = salePersistencePort;
    }


    @Override
    public void createSale(List<ArticleQuantity> articleQuantityList, Long userId) {
        Sale createdSale = this.initializeSale(articleQuantityList,userId);
        List<ArticleQuantity> quantitiesToRestore = new ArrayList<>();
        try {
            for (ArticleQuantity articleQuantity : articleQuantityList) {
                Long articleId = articleQuantity.getArticleId();
                Integer requestedQuantity = articleQuantity.getQuantity();
                quantitiesToRestore.add(new ArticleQuantity(articleId, requestedQuantity));
                articlePersistencePort.updateArticleQuantity(new ArticleQuantity(articleId, -requestedQuantity));
            }

            updateSaleStatus(createdSale, Constants.SALE_STATUS_COMPLETED);

        } catch (Exception e) {
            restoreStock(quantitiesToRestore);
        }
    }

    private Sale initializeSale(List<ArticleQuantity> articleQuantityList, Long userId) {
        Sale sale = new Sale(userId, Constants.SALE_STATUS_PENDING);
        List<Long> outOfStockArticleIds = new ArrayList<>();
        List<SaleItem> saleItemList = new ArrayList<>();
        Double totalPrice = Constants.DOUBLE_ZERO;

        for (ArticleQuantity articleQuantity : articleQuantityList) {
            Long articleId = articleQuantity.getArticleId();
            Integer requestedQuantity = articleQuantity.getQuantity();

            Article article = articlePersistencePort.getArticleById(articleId);
            totalPrice += requestedQuantity * article.getPrice();

            if (!isStockAvailable(article, requestedQuantity)) {
                outOfStockArticleIds.add(articleId);
            }
            SaleItem saleItem = new SaleItem(articleId, requestedQuantity,article.getPrice());
            saleItemList.add(saleItem);
        }
        sale.setTotalPrice(totalPrice);
        sale.setItems(saleItemList);
        Sale createdSale = this.salePersistencePort.initializeSale(sale);

        if (!outOfStockArticleIds.isEmpty()) {
            updateSaleStatus(createdSale, Constants.SALE_STATUS_FAILED);
            throw new SaleCreationException(Constants.EXCEPTION_OUT_OF_STOCK + outOfStockArticleIds);
        }
        return createdSale;
    }

    private void updateSaleStatus(Sale createdSale, String status) {
        createdSale.setModifiedDate(LocalDateTime.now());
        createdSale.setStatus(status);
        this.salePersistencePort.updateSale(createdSale);
    }

    private boolean isStockAvailable(Article article, Integer requestedQuantity) {
        return article.getQuantity() >= requestedQuantity;
    }

    private void restoreStock(List<ArticleQuantity> quantitiesToRestore) {
        for (ArticleQuantity articleQuantity : quantitiesToRestore) {
            articlePersistencePort.updateArticleQuantity(articleQuantity);
        }
    }

}
