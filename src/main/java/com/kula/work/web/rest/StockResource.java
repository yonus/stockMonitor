package com.kula.work.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kula.work.config.Constants;
import com.kula.work.security.AuthoritiesConstants;
import com.kula.work.service.StockService;
import com.kula.work.service.dto.StockDTO;
import com.kula.work.service.dto.UserStockDTO;
import com.kula.work.web.rest.errors.BadRequestAlertException;
import com.kula.work.web.rest.errors.StockCodeAlreadyUsedException;
import com.kula.work.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stock")
public class StockResource {
    private final Logger log = LoggerFactory.getLogger(UserResource.class);


    private StockService stockService;

    public StockResource(StockService stockService){
        this.stockService  = stockService;
    }

    @GetMapping("/allStocks")
    public List<StockDTO> getAllStocks(){
        return stockService.getStockDTOS();
    }


    @GetMapping("/{code}")
    StockDTO getStock(@PathVariable String code){
        return stockService.getStock(code);

    }

    @Secured(AuthoritiesConstants.ADMIN)
    @DeleteMapping("/{code}")
    ResponseEntity<Void> deleteStock(@PathVariable String code){
        stockService.deleteStock(code);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert( "The stock is deleted with code " + code, code)).build();
    }

    @Secured(AuthoritiesConstants.ADMIN)
    @PostMapping()
    ResponseEntity<StockDTO>  saveStock(@Valid @RequestBody  StockDTO stockDTO) throws BadRequestAlertException, URISyntaxException {
        if(stockDTO.getId() != null){
            throw new BadRequestAlertException("A new stock cannot already have an ID", "stockManagement", "idexists");

        }
        if(stockService.getStock(stockDTO.getCode()) != null){
            throw new StockCodeAlreadyUsedException();
        }
        StockDTO newStockDTO = stockService.saveStock(stockDTO);
        return ResponseEntity.created(new URI("/api/stock/" + stockDTO.getCode()))
            .headers(HeaderUtil.createAlert( "A stock is created with identifier " + stockDTO.getCode(), stockDTO.getCode()))
            .body(stockDTO);
    }


    @Secured(AuthoritiesConstants.ADMIN)
    @PutMapping
    ResponseEntity<StockDTO>  updateStock(@Valid @RequestBody  StockDTO stockDTO) throws BadRequestAlertException, URISyntaxException {
        if(stockDTO.getId() == null){
            throw new BadRequestAlertException("A stock  id cannot be null ID", "stockManagement", "idexists");

        }

        if(stockService.getStock(stockDTO.getCode()) == null){
            throw new BadRequestAlertException("A stock code cannot be found system", "stockManagement", "CodeNotFound");
        }


        Optional<StockDTO> newStockDTO = stockService.updateStock(stockDTO);
        if(!newStockDTO.isPresent()){
            throw new BadRequestAlertException("A stock  cannot be updated ", "stockManagement", "NotUpdated");

        }
        return ResponseUtil.wrapOrNotFound(newStockDTO,
            HeaderUtil.createAlert("A stock is updated with identifier " + stockDTO.getCode(), stockDTO.getCode()));

    }

    @GetMapping("user/{login}")
    public UserStockDTO getUserStocks(@PathVariable String login){
         return stockService.getUserStocks(login);
    }




}
