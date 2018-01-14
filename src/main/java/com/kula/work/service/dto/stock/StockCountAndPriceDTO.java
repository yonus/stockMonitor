package com.kula.work.service.dto.stock;

public class StockCountAndPriceDTO {

    private StockDTO stockDTO;

    private StockCurrentPriceDTO currentPriceDTO;

    private  int  totalCount;

    public StockDTO getStockDTO() {
        return stockDTO;
    }

    public void setStockDTO(StockDTO stockDTO) {
        this.stockDTO = stockDTO;
    }

    public StockCurrentPriceDTO getCurrentPriceDTO() {
        return currentPriceDTO;
    }

    public void setCurrentPriceDTO(StockCurrentPriceDTO currentPriceDTO) {
        this.currentPriceDTO = currentPriceDTO;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
