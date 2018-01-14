import { Component, OnInit } from '@angular/core';
import {StockService} from "../shared/stock/stock.service";
import {StockModel} from "../shared/stock/stock.model";
import {StockPriceModel} from "../shared/stock/stock-price.model";
import {JhiEventManager} from "ng-jhipster";
import {timeout} from "rxjs/operator/timeout";

@Component({
    selector: 'stock-home',
    templateUrl: './home.component.html',
    styleUrls: [
        'home.css'
    ]
})
export class HomeComponent implements OnInit {

    private stocks:StockPriceModel[];
    private stockValues:Iterable<StockPriceModel>;
    constructor(private stockService:StockService,
                private eventManager :JhiEventManager) {
    }

    ngOnInit() {
        this.stockService.getStockPrices().subscribe((values:StockPriceModel[])=>{

            this.stocks = values;
            this.registerPriceChange();



        });
    }

    registerPriceChange(){
        this.eventManager.subscribe("priceChangeEvent",(stockPrice) =>{
            stockPrice = stockPrice.content;
            let currentStockPriceIndex = this.stocks.findIndex(stock => stock.code == stockPrice.code);
            if(currentStockPriceIndex > -1){
                this.stocks[currentStockPriceIndex].price = stockPrice.price;
                this.stocks[currentStockPriceIndex].lastFetchTime = stockPrice.lastFetchTime;
            }


        });
    }

    trackIdentity(index , item:StockPriceModel){
        return item.code;
    }

    registerAuthenticationSuccess() {
    }

    isAuthenticated() {
    }

    login() {
    }
}
