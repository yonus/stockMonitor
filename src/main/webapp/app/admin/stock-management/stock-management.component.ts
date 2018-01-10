import { Component, OnInit } from '@angular/core';
import {StockModel} from "../../shared/stock/stock.model";
import {StockService} from "../../shared/stock/stock.service";
import {JhiEventManager} from "ng-jhipster";

@Component({
  selector: 'stock-management-home',
  templateUrl: './stock-management.component.html',
  styles: []
})
export class StockManagementComponent implements OnInit {
   stocks:StockModel[];

  constructor(private stockService:StockService,
              private  eventManager:JhiEventManager) { }

  ngOnInit() {
      this.loadStocks();
      this.registerChangeInStock();
  }

  trackIdentity(index , item:StockModel){
      return item.code;
  }

  loadStocks(){
      this.stockService.getStocks().subscribe(values => {
          this.stocks = values;
      })
  }

  registerChangeInStock(){
      this.eventManager.subscribe('stockAddedOrEdited', (response) => this.loadStocks());
  }

  confirmDelete(code:string){
       this.stockService.delete(code).subscribe(res =>{
           this.eventManager.broadcast({ name: 'stockAddedOrEdited',
               content: 'Deleted a user'});

       });
  }

}
