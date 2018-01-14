package com.kula.work.service.mapper;

import com.kula.work.domain.User;
import com.kula.work.domain.stock.entity.StockModelEntity;
import com.kula.work.domain.stock.entity.UserStockModelEntity;
import com.kula.work.service.StockPriceService;
import com.kula.work.service.dto.stock.StockCountAndPriceDTO;
import com.kula.work.service.dto.stock.StockCurrentPriceDTO;
import com.kula.work.service.dto.stock.StockDTO;
import com.kula.work.service.dto.stock.UserStockDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserStockMapper {

    private StockMapper stockMapper;
    private UserMapper userMapper;
    private StockPriceService stockPriceService;

    public  UserStockMapper(StockMapper stockMapper,
                            UserMapper userMapper,
                            StockPriceService stockPriceService){
        this.stockMapper = stockMapper;
        this.userMapper = userMapper;
        this.stockPriceService = stockPriceService;

    }

    public UserStockDTO userStockModelEntityToUserStockDTO(User user, List<UserStockModelEntity> userStockModelEntities){
        UserStockDTO userStockDTO = new UserStockDTO();
        userStockDTO.setUserDTO(userMapper.userToUserDTO(user));
        List<StockCountAndPriceDTO> stockCountAndPriceDTOS = userStockModelEntities.stream().
            filter(entity -> entity.getCount() > 0).
            map(entity->{
               StockModelEntity stockModelEntity = entity.getStockModelEntity();
            StockCountAndPriceDTO stockCountAndPriceDTO = new StockCountAndPriceDTO();
            stockCountAndPriceDTO.setStockDTO(stockMapper.stockEntityToStockDTO(stockModelEntity));
            stockCountAndPriceDTO.setTotalCount(entity.getCount());
            StockCurrentPriceDTO stockCurrentPriceDTO = stockPriceService.getStockCurrentPrice(stockModelEntity.getCode());
            stockCountAndPriceDTO.setCurrentPriceDTO(stockCurrentPriceDTO);
            return stockCountAndPriceDTO;
        }).collect(Collectors.toList());

        userStockDTO.setStockCountAndPriceDTOS(stockCountAndPriceDTOS);
        return userStockDTO;
    }


}
