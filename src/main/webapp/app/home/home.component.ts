import { Component, OnInit } from '@angular/core';
import {StockService} from "../shared/stock/stock.service";
import {StockModel} from "../shared/stock/stock.model";

@Component({
    selector: 'stock-home',
    templateUrl: './home.component.html',
    styleUrls: [
        'home.css'
    ]
})
export class HomeComponent implements OnInit {

    private stocks:StockModel[];
    constructor(private stockService:StockService) {
    }

    ngOnInit() {
        this.stockService.getStocks().subscribe((values)=>{
            this.stocks = values;
            console.log(values);
        });
    }

    registerAuthenticationSuccess() {
    }

    isAuthenticated() {
    }

    login() {
    }
}
