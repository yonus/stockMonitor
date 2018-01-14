import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Observable} from "rxjs/Observable";
import {StockModalService, StockService} from "../shared";
import {StockTradeCommandCompoment} from "./stock-trade-command.component";
import {COMMAND_BUY, COMMAND_SELL} from "./user-stock.constant";
import {JhiEventManager} from "ng-jhipster";

@Component({
  selector: 'user-stock',
  templateUrl: './user-stock.component.html',
    styleUrls: ['user-stock.css']
})
export class UserStockComponent implements OnInit {
   private routeSubscriber;
   private userStockInfo;
   private login;

  constructor(private route: ActivatedRoute,
              private stockService:StockService,
              private stockModalService: StockModalService,
              private eventManager:JhiEventManager) {

  }

  ngOnInit() {
   this.routeSubscriber = this.route.params.subscribe(params =>{
       let login = params["login"];
       this.login = login;
       this.getUserStokInfo(login).subscribe(userStockInfo => {
           this.userStockInfo = userStockInfo;
       })

       this.eventManager.subscribe("userStockUpdated",()=>{
           this.getUserStokInfo(login).subscribe(userStockInfo => {
               this.userStockInfo = userStockInfo;
           });
       })
   })
  }

  getUserStokInfo(login:string):Observable<any>{
      return this.stockService.getUserStockInfo(login);
  }

  buyStock():void{
      this.stockService.getStocks().subscribe(stocks =>{
          this.stockModalService.openTradeCommand(StockTradeCommandCompoment as Component,this.login,COMMAND_BUY,null,stocks);
      });

  }

  sellStock():void{
      this.stockModalService.openTradeCommand(StockTradeCommandCompoment as Component,this.login,COMMAND_SELL,this.userStockInfo,null);
  }


  ngOnDestroy() {
        this.routeSubscriber.unsubscribe();
  }

}
