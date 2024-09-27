package com.emazon.transactions.domain.usecase;


import com.emazon.transactions.domain.exceptions.SaleCreationException;
import com.emazon.transactions.domain.model.Article;
import com.emazon.transactions.domain.model.ArticleQuantity;
import com.emazon.transactions.domain.model.Sale;
import com.emazon.transactions.domain.model.SaleReport;
import com.emazon.transactions.domain.spi.IArticlePersistencePort;
import com.emazon.transactions.domain.spi.ICartPersistencePort;
import com.emazon.transactions.domain.spi.IReportPersistencePort;
import com.emazon.transactions.domain.spi.ISalePersistencePort;
import com.emazon.transactions.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class SaleUseCaseTest {

    @Mock
    private IArticlePersistencePort articlePersistencePort;
    @Mock
    private ISalePersistencePort salePersistencePort;
    @Mock
    private ICartPersistencePort cartPersistencePort;
    @Mock
    private IReportPersistencePort reportPersistencePort;

    @InjectMocks
    private SaleUseCase saleUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void SaleUseCase_CreateSale_ShouldCreateSaleSuccessfully_WhenAllArticlesHaveSufficientStock() {
        Long userId = 1L;
        List<ArticleQuantity> articleQuantities = List.of(
                new ArticleQuantity(1L, 2),
                new ArticleQuantity(2L, 3)
        );

        Article article1 = new Article(1L,10, 100.0);
        Article article2 = new Article(2L, 5, 200.0);

        Mockito.when(articlePersistencePort.getArticleById(1L)).thenReturn(article1);
        Mockito.when(articlePersistencePort.getArticleById(2L)).thenReturn(article2);

        Sale createdSale = new Sale(userId, Constants.SALE_STATUS_PENDING);
        createdSale.setTotalPrice(600D);
        createdSale.setId(1L);

        Mockito.when(salePersistencePort.initializeSale(Mockito.any(Sale.class))).thenReturn(createdSale);

        saleUseCase.createSale(articleQuantities, userId);

        Mockito.verify(salePersistencePort).updateSale(Mockito.any(Sale.class));
        Mockito.verify(reportPersistencePort).saveSaleReport(Mockito.any(SaleReport.class));
        Mockito.verify(cartPersistencePort, Mockito.times(2)).removeArticleFromCart(Mockito.anyLong());
    }

    @Test
    void SaleUseCase_CreateSale_ShouldThrowSaleCreationException_WhenAnyArticleIsOutOfStock() {
        Long userId = 1L;
        List<ArticleQuantity> articleQuantities = List.of(
                new ArticleQuantity(1L, 2),
                new ArticleQuantity(2L, 10) //
        );

        Article article1 = new Article(1L,  10, 100.0);
        Article article2 = new Article(2L,  5, 200.0);

        Mockito.when(articlePersistencePort.getArticleById(1L)).thenReturn(article1);
        Mockito.when(articlePersistencePort.getArticleById(2L)).thenReturn(article2);

        Sale createdSale = new Sale(userId, Constants.SALE_STATUS_PENDING);
        createdSale.setId(1L);

        Mockito.when(salePersistencePort.initializeSale(Mockito.any(Sale.class))).thenReturn(createdSale);

       assertThrows(SaleCreationException.class, () -> {
            saleUseCase.createSale(articleQuantities, userId);
        });
    }

}