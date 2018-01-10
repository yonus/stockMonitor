package com.kula.work.service.dto;

import java.util.List;

public class UserStockDTO {
    private  UserDTO userDTO;
    private List<StockDTO> stockDTOS;

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

    public List<StockDTO> getStockDTOS() {
        return stockDTOS;
    }

    public void setStockDTOS(List<StockDTO> stockDTOS) {
        this.stockDTOS = stockDTOS;
    }
}
