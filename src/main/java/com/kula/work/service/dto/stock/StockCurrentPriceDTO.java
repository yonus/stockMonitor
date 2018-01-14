package com.kula.work.service.dto.stock;

import java.io.Serializable;
import java.time.LocalDateTime;

public class StockCurrentPriceDTO implements Serializable {
    /**
     * Stock code
     */
    private String code;


    private StockDTO stockDTO;

    /**
     * Stock current price
     */
    private float price;

    /**
     * date shows when this price is fetched from external system
     */
    private LocalDateTime lastFetchTime;

    public StockCurrentPriceDTO(){}

    public StockCurrentPriceDTO(String code , float price , LocalDateTime lastFetchTime){
        this.code = code;
        this.price = price;
        this.lastFetchTime = lastFetchTime;

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public LocalDateTime getLastFetchTime() {
        return lastFetchTime;
    }

    public StockDTO getStockDTO() {
        return stockDTO;
    }

    public void setStockDTO(StockDTO stockDTO) {
        this.stockDTO = stockDTO;
    }

    public void setLastFetchTime(LocalDateTime lastFetchTime) {
        this.lastFetchTime = lastFetchTime;
    }

    @Override
    public boolean equals(Object obj) {
        return ((StockCurrentPriceDTO)obj).code == this.code;
    }

    @Override
    public int hashCode() {
        return this.code.hashCode();
    }
}
