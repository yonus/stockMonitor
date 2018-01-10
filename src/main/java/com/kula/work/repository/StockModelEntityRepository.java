package com.kula.work.repository;

import com.kula.work.domain.stock.entity.StockModelEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface StockModelEntityRepository extends JpaRepository<StockModelEntity,Long> {
        String ALL_STOCK_CACHE_NAME ="ALL_STOCK_CACHE_NAME" ;
        String STOCK_BY_CODE_CACHE_NAME ="STOCK_BY_CODE_CACHE_NAME" ;

    @Cacheable(cacheNames = ALL_STOCK_CACHE_NAME)
    @Override
    List<StockModelEntity> findAll();

    @Cacheable(cacheNames = STOCK_BY_CODE_CACHE_NAME)
    Optional<StockModelEntity> findByCode(String code);


}
