package com.emazon.transactions.infrastructure.configuration;


import com.emazon.transactions.domain.api.ISaleServicePort;
import com.emazon.transactions.domain.api.ISecurityServicePort;
import com.emazon.transactions.domain.api.ISupplyServicePort;
import com.emazon.transactions.domain.spi.IArticlePersistencePort;
import com.emazon.transactions.domain.spi.ISalePersistencePort;
import com.emazon.transactions.domain.spi.ISecurityPersistencePort;
import com.emazon.transactions.domain.spi.ISupplyPersistencePort;
import com.emazon.transactions.domain.usecase.SaleUseCase;
import com.emazon.transactions.domain.usecase.SecurityUseCase;
import com.emazon.transactions.domain.usecase.SupplyUseCase;
import com.emazon.transactions.infrastructure.output.feign.adapter.ArticleFeignAdapter;
import com.emazon.transactions.infrastructure.output.feign.client.ArticleFeignClient;
import com.emazon.transactions.infrastructure.output.feign.mapper.UpdateQuantityFeignMapper;
import com.emazon.transactions.infrastructure.output.jpa.adapter.SaleJpaAdapter;
import com.emazon.transactions.infrastructure.output.jpa.adapter.SupplyJpaAdapter;
import com.emazon.transactions.infrastructure.output.jpa.mapper.SaleEntityMapper;
import com.emazon.transactions.infrastructure.output.jpa.mapper.SupplyEntityMapper;
import com.emazon.transactions.infrastructure.output.jpa.repository.ISaleItemRepository;
import com.emazon.transactions.infrastructure.output.jpa.repository.ISaleRepository;
import com.emazon.transactions.infrastructure.output.jpa.repository.ISupplyRepository;
import com.emazon.transactions.infrastructure.output.security.adapter.SecurityAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ISupplyRepository supplyRepository;
    private final SupplyEntityMapper supplyEntityMapper;

    private final ISaleRepository saleRepository;
    private final ISaleItemRepository saleItemRepository;
    private final SaleEntityMapper saleEntityMapper;


    private final ArticleFeignClient articleFeignClient;
    private final UpdateQuantityFeignMapper updateQuantityFeignMapper;

    @Bean
    public ISupplyPersistencePort supplyPersistencePort(){
        return new SupplyJpaAdapter(supplyRepository, supplyEntityMapper);
    }

    @Bean
    public ISalePersistencePort salePersistencePort(){
        return new SaleJpaAdapter(saleRepository,saleItemRepository,saleEntityMapper);
    }

    @Bean
    public IArticlePersistencePort articlePersistencePort(){
        return new ArticleFeignAdapter(articleFeignClient,updateQuantityFeignMapper);
    }

    @Bean
    public ISupplyServicePort supplyServicePort()  {
        return new SupplyUseCase(supplyPersistencePort(),articlePersistencePort());
    }

    @Bean
    public ISaleServicePort saleServicePort()  {
        return new SaleUseCase(articlePersistencePort(),salePersistencePort());
    }

    @Bean
    public ISecurityServicePort securityServicePort() {
        return new SecurityUseCase(securityPersistencePort());
    }

    @Bean
    public ISecurityPersistencePort securityPersistencePort(){
        return new SecurityAdapter();
    }
}
