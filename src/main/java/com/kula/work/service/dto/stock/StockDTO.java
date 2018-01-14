package com.kula.work.service.dto.stock;

import com.kula.work.domain.stock.entity.StockModelEntity;

import java.time.Instant;
import java.util.Date;


public class StockDTO {
    private Long id;
    private String code;
    private String name;
    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;



    public  StockDTO(){

    }

    public StockDTO(StockModelEntity stockModelEntity){
          if(stockModelEntity != null){
              this.id = stockModelEntity.getId();
              this.code = stockModelEntity.getCode();
              this.name = stockModelEntity.getName();
              this.createdDate = stockModelEntity.getCreatedDate();
              this.lastModifiedDate = stockModelEntity.getLastModifiedDate();
              this.createdBy = stockModelEntity.getCreatedBy();
              this.lastModifiedBy = stockModelEntity.getLastModifiedBy();
          }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
