package com.kula.work.service.mapper;

import com.kula.work.domain.User;
import com.kula.work.domain.stock.entity.UserStockModelEntity;
import com.kula.work.service.dto.StockDTO;
import com.kula.work.service.dto.UserDTO;
import com.kula.work.service.dto.UserStockDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserStockMapper {


    private StockMapper stockMapper;
    private UserMapper userMapper;

    public UserStockDTO userStockModelEntityToUserStockDTO(User user, List<UserStockModelEntity> userStockModelEntities){
        UserStockDTO userStockDTO = new UserStockDTO();
        userStockDTO.setUserDTO(userMapper.userToUserDTO(user));
        List<StockDTO> stockDTOS = userStockModelEntities.stream().map(entity->stockMapper.stockEntityToStockDTO(entity.getStockModelEntity()))
            .collect(Collectors.toList());
        userStockDTO.setStockDTOS(stockDTOS);
        return userStockDTO;
    }
}
