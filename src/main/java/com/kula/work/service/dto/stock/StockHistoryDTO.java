package com.kula.work.service.dto.stock;

import java.time.LocalDateTime;

public class StockHistoryDTO {
    private String code;
    private float price;
    private LocalDateTime date;


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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
