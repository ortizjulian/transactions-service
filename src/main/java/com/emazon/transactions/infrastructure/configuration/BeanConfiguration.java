package com.emazon.transactions.infrastructure.configuration;


import com.emazon.transactions.domain.api.ISupplyServicePort;
import com.emazon.transactions.domain.spi.ISupplyPersistencePort;
import com.emazon.transactions.domain.usecase.SupplyUseCase;
import com.emazon.transactions.infrastructure.output.jpa.adapter.SupplyJpaAdapter;
import com.emazon.transactions.infrastructure.output.jpa.mapper.SupplyEntityMapper;
import com.emazon.transactions.infrastructure.output.jpa.repository.SupplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final SupplyRepository supplyRepository;
    private final SupplyEntityMapper supplyEntityMapper;

    @Bean
    public ISupplyPersistencePort supplyPersistencePort(){
        return new SupplyJpaAdapter(supplyRepository, supplyEntityMapper);
    }

    @Bean
    public ISupplyServicePort supplyServicePort()  {
        return new SupplyUseCase(supplyPersistencePort() );
    }
}
