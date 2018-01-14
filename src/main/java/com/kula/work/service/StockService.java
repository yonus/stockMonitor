package com.kula.work.service;

import com.kula.work.domain.User;
import com.kula.work.domain.stock.entity.StockModelEntity;
import com.kula.work.domain.stock.entity.UserStockModelEntity;
import com.kula.work.repository.StockModelEntityRepository;
import com.kula.work.repository.UserRepository;
import com.kula.work.repository.UserStockModelEntityRepository;
import com.kula.work.service.dto.stock.*;
import com.kula.work.service.dto.user.UserDTO;
import com.kula.work.service.mapper.StockMapper;
import com.kula.work.service.mapper.UserStockMapper;
import com.kula.work.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);
    private StockModelEntityRepository stockModelEntityRepository;
    private UserRepository userRepository;
    private UserStockModelEntityRepository userStockModelEntityRepository;

    private StockMapper stockMapper;
    private UserStockMapper userStockMapper;
    private CacheManager cacheManager;
    private  StockPriceService stockPriceService;


    public StockService(StockModelEntityRepository stockModelEntityRepository,
                        StockMapper stockMapper,
                        CacheManager cacheManager,
                        UserRepository userRepository,
                        UserStockModelEntityRepository userStockModelEntityRepository,
                        UserStockMapper userStockMapper,
                        StockPriceService stockPriceService
                        ){
        this.stockMapper = stockMapper;
        this.stockModelEntityRepository = stockModelEntityRepository;
        this.cacheManager = cacheManager;
        this.userRepository = userRepository;
        this.userStockModelEntityRepository = userStockModelEntityRepository;
        this.userStockMapper = userStockMapper;
        this.stockPriceService = stockPriceService;
    }


    public  List<StockDTO> getStockDTOS(){
       return  stockModelEntityRepository.findAll().stream().
            map(stockMapper::stockEntityToStockDTO).collect(Collectors.toList());
    }


    public StockDTO getStock(String code){
        return stockModelEntityRepository.findByCode(code).map(stockMapper::stockEntityToStockDTO).orElse(null);
    }

    public void deleteStock(String code){
         stockModelEntityRepository.findByCode(code).ifPresent(s -> {
             stockModelEntityRepository.delete(s);
             clearCaches(stockMapper.stockEntityToStockDTO(s));

         });
    }

    public StockDTO saveStock(StockDTO stockDTO){

        StockModelEntity stockModelEntity =  stockModelEntityRepository.save(stockMapper.stockDTOToStockModelEntity(stockDTO));
        clearCaches(stockDTO);
        return stockMapper.stockEntityToStockDTO(stockModelEntity);
    }


    public Optional<StockDTO> updateStock(StockDTO stockDTO){
       return Optional.of(stockModelEntityRepository.findOne(stockDTO.getId()))
            .map(stockModelEntity -> {
                 stockModelEntity.setName(stockDTO.getName());
                clearCaches(stockDTO);
                stockModelEntityRepository.save(stockModelEntity);
                return stockModelEntity;
            }).map(StockDTO::new);
    }


    public UserStockDTO getUserStocks(UserDTO userDto){
        User user = userRepository.findOne(userDto.getId());
        return getUserStocks(user);
    }

    public List<StockCurrentPriceDTO> getStockPrices(){
        List<StockDTO> stockDTOS = getStockDTOS();
        return stockDTOS.stream().map(stockDTO -> {
            StockCurrentPriceDTO stockCurrentPriceDTO = stockPriceService.getStockCurrentPrice(stockDTO.getCode());
            stockCurrentPriceDTO.setStockDTO(stockDTO);
            return stockCurrentPriceDTO;
        }).collect(Collectors.toList());
    }

    @Transactional
    public UserStockDTO getUserStocks(String loginName){
       return userRepository.findOneByLogin(loginName).map(this::getUserStocks).orElse(null);
    }

    public UserStockDTO getUserStocks(User user){
        return userStockModelEntityRepository.findUserStockModelEntitiesByUser_Id(user.getId())
            .map(result -> userStockMapper.userStockModelEntityToUserStockDTO(user,result)).orElse(new UserStockDTO(new UserDTO(user)));
    }
    @Transactional
    public void executeCommand(StockCommandDTO stockCommandDTO){
       User user =  userRepository.findOneByLogin(stockCommandDTO.getLogin()).orElseThrow(() -> new  BadRequestAlertException("User is not found","User","USERNOTFOUND"));
       StockModelEntity stockModelEntity = stockModelEntityRepository.findByCode(stockCommandDTO.getCode()).orElseThrow(()-> new  BadRequestAlertException("Stock is not found","Stock","STOCKNOTFOUND"));
       UserStockModelEntity userStockModelEntity = userStockModelEntityRepository.findUserStockModelEntitiesByUser_IdAndStockModelEntity_Code(user.getId(),stockModelEntity.getCode()).orElse(new UserStockModelEntity(user,stockModelEntity));
       if(StockCommandType.SELL.equals(stockCommandDTO.getCommand())){
           if(stockCommandDTO.getCount() > userStockModelEntity.getCount()){
               throw  new BadRequestAlertException(String.format("You can't sell stocks more than %d",userStockModelEntity.getCount()),"UserStockSELL","NOTENOUGHBALANCE");
           }

           userStockModelEntity.setCount(userStockModelEntity.getCount() - stockCommandDTO.getCount());
       }else if(StockCommandType.BUY.equals(stockCommandDTO.getCommand())){
           userStockModelEntity.setCount(userStockModelEntity.getCount() + stockCommandDTO.getCount());
       }
       userStockModelEntityRepository.save(userStockModelEntity);
    }

    private void clearCaches(StockDTO stockDTO){
        cacheManager.getCache(StockModelEntityRepository.ALL_STOCK_CACHE_NAME).clear();
        cacheManager.getCache(StockModelEntityRepository.STOCK_BY_CODE_CACHE_NAME).evict(stockDTO.getCode());
    }


}
