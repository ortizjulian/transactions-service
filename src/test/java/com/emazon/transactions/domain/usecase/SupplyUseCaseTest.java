package com.emazon.transactions.domain.usecase;

import com.emazon.transactions.domain.model.Supply;
import com.emazon.transactions.domain.spi.IArticlePersistencePort;
import com.emazon.transactions.domain.spi.ISecurityPersistencePort;
import com.emazon.transactions.domain.spi.ISupplyPersistencePort;
import com.emazon.transactions.infrastructure.output.feign.exceptions.BadRequestException;
import com.emazon.transactions.infrastructure.output.feign.exceptions.NotFoundException;
import feign.FeignException;
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
    void SupplyUseCase_AddSupply_ShouldUpdateArticleQuantityAndSaveSupply() {
        Supply supply = new Supply(null, 1L, 50, 1L);
        String token = "valid-token";

        Mockito.doNothing().when(articlePersistencePort).updateArticleQuantity(Mockito.any());

        Mockito.doNothing().when(supplyPersistencePort).saveSupply(supply);

        supplyUseCase.addSupply(supply, token);

        Mockito.verify(securityPersistencePort).setToken(token);

        Mockito.verify(supplyPersistencePort).saveSupply(supply);

    }
}