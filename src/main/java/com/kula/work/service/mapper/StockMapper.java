package com.kula.work.service.mapper;

import com.kula.work.domain.stock.entity.StockModelEntity;
import com.kula.work.service.dto.StockDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StockMapper {


    public StockDTO stockEntityToStockDTO(StockModelEntity stockModelEntity){
          return new StockDTO(stockModelEntity);
    }

    public List<StockDTO> stockEntitiesToStockDTOs(List<StockModelEntity> stockModelEntitys){
        return stockModelEntitys.stream().filter(Objects::nonNull).map(this::stockEntityToStockDTO).collect(Collectors.toList());
    }



    public StockModelEntity stockDTOToStockModelEntity(StockDTO stockDTO){
        StockModelEntity stockModelEntity = new StockModelEntity();
        stockModelEntity.setCode(stockDTO.getCode());
        stockModelEntity.setId(stockDTO.getId());
        stockModelEntity.setName(stockDTO.getName());
        return  stockModelEntity;
    }

    public List<StockModelEntity> stockDTOsToStockModelEntities(List<StockDTO> stockDTOs){
        return stockDTOs.stream().filter(Objects::nonNull).map(this::stockDTOToStockModelEntity).collect(Collectors.toList());

    }





}
