package com.emazon.transactions.domain.usecase;

import com.emazon.transactions.domain.exceptions.ArticleNotFoundException;
import com.emazon.transactions.domain.exceptions.FeignClientUnexpectedResponseException;
import com.emazon.transactions.domain.model.StatusCodeEnum;
import com.emazon.transactions.domain.model.Supply;
import com.emazon.transactions.domain.model.UpdateQuantity;
import com.emazon.transactions.domain.spi.IArticlePersistencePort;
import com.emazon.transactions.domain.spi.ISecurityPersistencePort;
import com.emazon.transactions.domain.spi.ISupplyPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SupplyUseCaseTest {

    @Mock
    private ISupplyPersistencePort supplyPersistencePort;

    @Mock
    private IArticlePersistencePort articlePersistencePort;

    @Mock
    private ISecurityPersistencePort securityPersistencePort;

    @InjectMocks
    private SupplyUseCase supplyUseCase;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void SupplyUseCae_AddSupply_ShouldUpdateArticleQuantityAndSaveSupply() {

        Supply supply = new Supply(
                null,
                1L,
                50,
                1L
        );

        String token = "valid-token";

        Mockito.when(articlePersistencePort.updateArticleQuantity(Mockito.any()))
                .thenReturn(StatusCodeEnum.NO_CONTENT);

        Mockito.doNothing().when(supplyPersistencePort).saveSupply(supply);

        supplyUseCase.addSupply(supply, token);

        Mockito.verify(securityPersistencePort).setToken(token);
        Mockito.verify(supplyPersistencePort).saveSupply(supply);
        assertNotNull(supply.getTransactionDate(), "Transaction date should be set");
    }

    @Test
    void SupplyUseCase_AddSupply_WhenArticleNotFound_ShouldThrowArticleNotFoundException() {

        Supply supply = new Supply(
                null,
                1L,
                50,
                1L
        );

        String token = "valid-token";

        Mockito.when(articlePersistencePort.updateArticleQuantity(Mockito.any()))
                .thenReturn(StatusCodeEnum.NOT_FOUND);

        ArticleNotFoundException exception = assertThrows(ArticleNotFoundException.class, () -> {
            supplyUseCase.addSupply(supply, token);
        });

        assertEquals("Article not found with ID: 1", exception.getMessage());

        Mockito.verify(supplyPersistencePort, Mockito.never()).saveSupply(Mockito.any());

        Mockito.verify(securityPersistencePort).setToken(token);
    }

    @Test
    void SupplyUseCase_AddSupply_WhenFeignClientUnexpectedResponse_ShouldThrowFeignClientUnexpectedResponseException() {

        Supply supply = new Supply(
                null,
                1L,
                50,
                1L
        );

        String token = "valid-token";

        Mockito.when(articlePersistencePort.updateArticleQuantity(Mockito.any()))
                .thenReturn(StatusCodeEnum.BAD_REQUEST);
        FeignClientUnexpectedResponseException exception = assertThrows(FeignClientUnexpectedResponseException.class, () -> {
            supplyUseCase.addSupply(supply, token);
        });

        Mockito.verify(supplyPersistencePort, Mockito.never()).saveSupply(Mockito.any());

        Mockito.verify(securityPersistencePort).setToken(token);
    }


}