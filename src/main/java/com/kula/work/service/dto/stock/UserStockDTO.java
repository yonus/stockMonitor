package com.kula.work.service.dto.stock;

import com.kula.work.service.dto.user.UserDTO;

import java.util.List;

/**
 * DTO for user stock Info with its price and count
 */
public class UserStockDTO {

    /**
     * User Info
     */
    private UserDTO userDTO;

    /**
     * All stocks info  that user have
     */
    private  List<StockCountAndPriceDTO> stockCountAndPriceDTOS;


    public UserStockDTO(){}

    public UserStockDTO(UserDTO userDTO){
        this.userDTO = userDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public List<StockCountAndPriceDTO> getStockCountAndPriceDTOS() {
        return stockCountAndPriceDTOS;
    }

    public void setStockCountAndPriceDTOS(List<StockCountAndPriceDTO> stockCountAndPriceDTOS) {
        this.stockCountAndPriceDTOS = stockCountAndPriceDTOS;
    }
}
