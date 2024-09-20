package com.emazon.transactions.domain.usecase;

import com.emazon.transactions.domain.exceptions.UnKnownNextSupplyDateException;
import com.emazon.transactions.domain.model.Supply;
import com.emazon.transactions.domain.spi.IArticlePersistencePort;
import com.emazon.transactions.domain.spi.ISupplyPersistencePort;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.emazon.transactions.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;


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

    @Test
    void SupplyUseCase_NextSupplyDate_ShouldReturnNextSupplyDate() {
        Long articleId = 1L;
        LocalDateTime lastSupplyDate = LocalDateTime.now().minusMonths(1);
        Mockito.when(supplyPersistencePort.findLastSupplyDateByArticleId(articleId))
                .thenReturn(Optional.of(lastSupplyDate));

        LocalDateTime nextSupplyDate = supplyUseCase.nextSupplyDate(articleId);

        assertNotNull(nextSupplyDate);
        assertEquals(lastSupplyDate.plusMonths(Constants.ONE_MONTH), nextSupplyDate);
        Mockito.verify(supplyPersistencePort).findLastSupplyDateByArticleId(articleId);
    }

    @Test
    void SupplyUseCase_NextSupplyDate_ShouldThrowUnKnownNextSupplyDateException_WhenNoLastSupplyDateFound() {
        Long articleId = 1L;
        Mockito.when(supplyPersistencePort.findLastSupplyDateByArticleId(articleId))
                .thenReturn(Optional.empty());

        assertThrows(UnKnownNextSupplyDateException.class, () -> {
            supplyUseCase.nextSupplyDate(articleId);
        });

        Mockito.verify(supplyPersistencePort).findLastSupplyDateByArticleId(articleId);
    }
}