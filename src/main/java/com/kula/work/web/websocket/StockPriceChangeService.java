package com.kula.work.web.websocket;

import com.kula.work.service.dto.stock.StockCurrentPriceDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
public class StockPriceChangeService {

    private static final String PRICE_CHANGE_TOPIC ="/topic/prices";

    private static final Logger log = LoggerFactory.getLogger(StockPriceChangeService.class);

    private final SimpMessageSendingOperations messagingTemplate;

    public StockPriceChangeService(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }


    public void publishPriceChange(StockCurrentPriceDTO stockCurrentPriceDTO){
        this.messagingTemplate.convertAndSend(PRICE_CHANGE_TOPIC,stockCurrentPriceDTO);
    }



}
