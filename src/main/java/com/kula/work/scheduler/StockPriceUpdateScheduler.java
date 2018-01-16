package com.kula.work.scheduler;

import com.kula.work.service.StockHistoryService;
import com.kula.work.service.StockPriceService;
import com.kula.work.service.StockService;
import com.kula.work.service.dto.stock.StockCurrentPriceDTO;
import com.kula.work.service.dto.stock.StockDTO;
import com.kula.work.web.websocket.StockPriceChangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 *
 */
@Component
public class StockPriceUpdateScheduler {
    private static final Logger log = LoggerFactory.getLogger(StockPriceUpdateScheduler.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private StockService stockService;
    private StockPriceService stockPriceService;
    private StockPriceChangeService stockPriceChangeService;
    private Random random;
    private StockHistoryService stockHistoryService;
    public  StockPriceUpdateScheduler(StockService stockService,
                                      StockPriceService  stockPriceService,
                                      StockPriceChangeService stockPriceChangeService,
                                      StockHistoryService stockHistoryService){

        this.stockService = stockService;
        this.stockPriceService = stockPriceService;
        this.stockPriceChangeService = stockPriceChangeService;
        this.stockHistoryService = stockHistoryService;

        this.random = new Random();
    }

    @Scheduled(fixedRate = 4000)
    public void updateStocksRandomly() {
        List<StockDTO>  allStockDTOS = stockService.getStockDTOS();
        allStockDTOS.forEach(stockDTO -> {
            StockCurrentPriceDTO  stockCurrentPriceDTO = stockPriceService.getStockCurrentPrice(stockDTO.getCode());
            if(stockCurrentPriceDTO.getPrice() == 0){
                stockCurrentPriceDTO.setPrice(random.nextFloat()*100);
                stockCurrentPriceDTO.setCode(stockDTO.getCode());
            }else{
                stockCurrentPriceDTO.setPrice(stockCurrentPriceDTO.getPrice() + (0.5f-random.nextFloat())*10);
            }
            stockCurrentPriceDTO.setLastFetchTime(LocalDateTime.now());
            stockPriceService.updateStockPrice(stockCurrentPriceDTO);
            stockHistoryService.insertStockHistory(stockCurrentPriceDTO);
            stockPriceChangeService.publishPriceChange(stockCurrentPriceDTO);
        });
        log.info("All Stocks is updated  at the time  {}", dateFormat.format(new Date()));
    }

}
