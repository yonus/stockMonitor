package com.kula.work.service;

import com.kula.work.service.dto.stock.StockCurrentPriceDTO;
import com.kula.work.service.dto.stock.StockHistoryDTO;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.List;
@Service
public class StockHistoryService {

    private static final  String HISTORY_COLLECTION = "STOCK_HISTORY";

    private MongoTemplate mongoTemplate;

    public StockHistoryService(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    public void insertStockHistory(StockHistoryDTO stockHistoryDTO){
        mongoTemplate.insert(stockHistoryDTO,HISTORY_COLLECTION);
    }


    public void insertStockHistory(StockCurrentPriceDTO stockCurrentPriceDTO){
        StockHistoryDTO stockHistoryDTO = new StockHistoryDTO();
        stockHistoryDTO.setCode(stockCurrentPriceDTO.getCode());
        stockHistoryDTO.setPrice(stockCurrentPriceDTO.getPrice());
        stockHistoryDTO.setDate(stockCurrentPriceDTO.getLastFetchTime());
        insertStockHistory(stockHistoryDTO);
    }

    public List<StockHistoryDTO> getStockHistory(String code){
       return  mongoTemplate.find(Query.query(where("code").is(code)).with(new Sort(Sort.Direction.DESC,"lastFetchTime")).limit(1000),StockHistoryDTO.class,HISTORY_COLLECTION);
    }
}
