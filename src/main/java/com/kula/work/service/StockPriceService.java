package com.kula.work.service;

import com.hazelcast.core.HazelcastInstance;
import com.kula.work.service.dto.stock.StockCurrentPriceDTO;
import com.kula.work.web.websocket.StockPriceChangeService;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * This class provides current prices of stock in systems
 * by using caches
 */
@Service
public class StockPriceService {
    private static final String STOCK_PRICE_CACHE_MAP = "STOCK_PRICE_CACHE_MAP";

    private HazelcastInstance hazelcastInstance;


     public StockPriceService(HazelcastInstance hazelcastInstance){
         this.hazelcastInstance = hazelcastInstance;
     }
    /**
     * return current price info of stock
     * @param code is stock code
     * @return
     */
    public StockCurrentPriceDTO getStockCurrentPrice(String code){
        Map<String,StockCurrentPriceDTO>  stockCurrentPriceDTOMap = hazelcastInstance.getMap(STOCK_PRICE_CACHE_MAP);
        return stockCurrentPriceDTOMap.getOrDefault(code,new StockCurrentPriceDTO(code,0, LocalDateTime.now()));
    }

    public void updateStockPrice(StockCurrentPriceDTO stockCurrentPriceDTO){
        Map<String,StockCurrentPriceDTO>  stockCurrentPriceDTOMap = hazelcastInstance.getMap(STOCK_PRICE_CACHE_MAP);
        stockCurrentPriceDTOMap.put(stockCurrentPriceDTO.getCode(),stockCurrentPriceDTO);
    }

}
