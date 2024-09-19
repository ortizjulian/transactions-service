package com.emazon.transactions.domain.usecase;

import com.emazon.transactions.domain.model.Supply;
import com.emazon.transactions.domain.spi.IArticlePersistencePort;
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


@ExtendWith(MockitoExtension.class)
class SupplyUseCaseTest {

    @Mock
    private ISupplyPersistencePort supplyPersistencePort;

    @Mock
    private IArticlePersistencePort articlePersistencePort;


    @InjectMocks
    private SupplyUseCase supplyUseCase;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void SupplyUseCase_AddSupply_ShouldUpdateArticleQuantityAndSaveSupply() {
        Supply supply = new Supply(null, 1L, 50, 1L);
        Mockito.doNothing().when(articlePersistencePort).updateArticleQuantity(Mockito.any());
        Mockito.doNothing().when(supplyPersistencePort).saveSupply(supply);
        supplyUseCase.addSupply(supply);
        Mockito.verify(supplyPersistencePort).saveSupply(supply);
    }
}